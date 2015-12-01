package ru.khasang.jclean.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.khasang.jclean.module.FolderContainer;
import ru.khasang.jclean.view.TemplateBuilder;
import ru.khasang.jclean.view.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowControl implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Pane top_pane;
    @FXML
    private VBox idVBoxBody;
    @FXML
    private Button plusButton;
    @FXML
    private Button startScan;

    private static double xOffset;
    private static double yOffset;
    TemplateBuilder templateBuilder = new TemplateBuilder();

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
    void draggWindow() {
        Stage stage = (Stage) top_pane.getScene().getWindow();
        top_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
        top_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });
    }

    public void addFolderAndChangeStage() {
        templateBuilder.setIdVBoxBody(idVBoxBody);
        Stage stage = (Stage) plusButton.getScene().getWindow();
//        stage.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            clearBody();
            renameScanButton();
            chooseDirectory(stage);
            addTable();
            addNewRow();
//        });
    }

    public void clearBody() {
        if (!isTableExist()) {
            idVBoxBody.getChildren().clear();
        }
    }

    public void renameScanButton() {
        startScan.setText("НАЧАТЬ ВЫБОРОЧНОЕ СКАНИРОВАНИЕ");
    }

    public void chooseDirectory(Stage stage) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            FolderContainer.getInstant().setFolder(selectedDirectory.getAbsolutePath());
        }
    }

    public void addTable() {
        if (!isTableExist()) {
            idVBoxBody.getChildren().add(templateBuilder.getTableView());
        }
    }

    public void addNewRow() {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindowControl.class.getClassLoader().getResource("fxml/tableView.fxml"));
            try {
                Parent root = loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
            TableController controller = loader.getController();
            controller.showFoldes();
    }

    public void restoreVBoxBody() {

    }

    public boolean isTableExist() {
        if(idVBoxBody.getChildren().isEmpty()) {
            return false;
        }else if(idVBoxBody.getChildren().get(0) instanceof TableView) {
            return true;
        }else {
            return false;
        }
    }

    public void startScan(ActionEvent actionEvent) {

    }
}
