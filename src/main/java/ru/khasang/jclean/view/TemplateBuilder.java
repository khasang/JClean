package ru.khasang.jclean.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;


public class TemplateBuilder {

    private VBox idVBoxBody;
    private TableView tableView;

    public TemplateBuilder() {
        initTable();
    }

    private void initTable() {
        String sceneFile = "fxml/tableView.fxml";
        Parent root;
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource(sceneFile);
            root = FXMLLoader.load(url);
            System.out.println("fxmlResource = " + sceneFile);
            tableView = (TableView) root;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public void setIdVBoxBody(VBox idVBoxBody) {
        this.idVBoxBody = idVBoxBody;
    }

    public VBox getIdVBoxBody() {
        return idVBoxBody;
    }
}
