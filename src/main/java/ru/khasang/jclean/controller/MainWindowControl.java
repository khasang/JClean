package ru.khasang.jclean.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowControl implements Initializable {

    @FXML private Pane closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML void closeButtonClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            System.exit(0);
        });
    }
}
