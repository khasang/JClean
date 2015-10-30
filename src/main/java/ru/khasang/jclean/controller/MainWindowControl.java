package ru.khasang.jclean.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.khasang.jclean.module.JContainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainWindowControl implements Initializable {

    JContainer container = new JContainer();
    ArrayList folders = new ArrayList();

    @FXML private Pane closeButton;
    @FXML private Button plusButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML void closeButtonClicked() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.exit(0);
        });
    }

    @FXML void addFolder() {
        Stage stage = (Stage) plusButton.getScene().getWindow();
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            folders.add("D:\\Videos\\");
            container.setFileFolders(folders);
            container.findAllIdenticalFiles();
            HashMap identical = container.getHexIdentical();
            System.exit(0);
        });
    }


}
