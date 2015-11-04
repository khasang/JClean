package ru.khasang.jclean.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowControl implements Initializable {

    @FXML
    private Pane closeButton;
    @FXML
    private Pane top_pane;
    private static double xOffset;
    private static double yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void closeButtonClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
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
}
