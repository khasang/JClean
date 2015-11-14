package ru.khasang.jclean.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowControl implements Initializable {

    public Button plusButton;
    public StackPane center_area;
    public FlowPane scan_btn_area;
    public GridPane table;
    public int row;

    @FXML
    private Button closeButton;
    @FXML
    private Pane top_pane;
    private static double xO;
    private static double yO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void closeButtonClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.exit(0);
        });
    }

    @FXML
    void dragWindow() {
        Stage stage = (Stage) top_pane.getScene().getWindow();
        top_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xO = stage.getX() - event.getScreenX();
                yO = stage.getY() - event.getScreenY();
            }
        });
        top_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xO);
                stage.setY(event.getScreenY() + yO);
            }
        });
    }

    @FXML
    void onPlusButton() {
        Stage stage = (Stage) plusButton.getScene().getWindow();
        plusButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final DirectoryChooser directoryChooser = new DirectoryChooser();
                final File selectedDirectory = directoryChooser.showDialog(stage);
                if (selectedDirectory != null) {
                    if (row > 0) {
                        addItem(selectedDirectory.getAbsolutePath());
                        row++;
                    } else {
                        clearCenterSpace();
                        addCustomScanButton();
                        addTableWithFolders();
                        addItem(selectedDirectory.getAbsolutePath());
                        row++;
                    }
                }
            }
        });
    }

    void clearCenterSpace() {
        center_area.getChildren().remove(0);
        scan_btn_area.getChildren().clear();
    }

    void addCustomScanButton() {
        String customScanButton = "fxml/customScanButton.fxml";
        try {
            scan_btn_area.getChildren().add(FXMLLoader.load(getClass().getClassLoader().getResource(customScanButton)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addTableWithFolders() {
        String tableWithFolders = "fxml/tableWithFolders.fxml";
        try {
            center_area.getChildren().add(0, FXMLLoader.load(getClass().getClassLoader().getResource(tableWithFolders)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addItem(String path) {
        Text text = new Text(path);
        text.setFont(Font.font("Microsoft JhengHei Bold", FontWeight.LIGHT, FontPosture.REGULAR, 16));
        ScrollPane scrollPane = (ScrollPane) center_area.getChildren().get(0);
        table = (GridPane) scrollPane.getContent();
        table.add(text, 1, row);
    }
}