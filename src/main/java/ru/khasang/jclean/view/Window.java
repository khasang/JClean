package ru.khasang.jclean.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.net.URL;

public class Window extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String sceneFile = "fxml/defaultWindow.fxml";
        javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("../src/main/resources/fonts/roboto-light.ttf"), 25);
//        javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("../src/main/resources/fonts/roboto-medium.ttf"), 14);
        javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("../src/main/resources/fonts/roboto-italic.ttf"), 14);
        javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("../src/main/resources/fonts/roboto-bold.ttf"), 14);

        Parent root;
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource(sceneFile);
            root = FXMLLoader.load(url);
            System.out.println("fxmlResource = " + sceneFile);
        } catch (Exception ex) {
            System.out.println("Exception on FXMLLoader.load()");
            System.out.println("  * url: " + url);
            System.out.println("  * " + ex);
            System.out.println("    ----------------------------------------\n");
            throw ex;
        }
        primaryStage.setTitle("JClean");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void draw() {
        launch();
    }
}
