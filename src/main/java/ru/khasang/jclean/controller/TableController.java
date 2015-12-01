package ru.khasang.jclean.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.khasang.jclean.module.FolderContainer;
import ru.khasang.jclean.view.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {

    private static ObservableList<TableRow> usersData = FXCollections.observableArrayList();
    @FXML
    private TableView<TableRow> tableUsers;
    @FXML
    private TableColumn<TableRow, Integer> idRow;
    @FXML
    private TableColumn<TableRow, String> idIconPath;
    @FXML
    private TableColumn<TableRow, String> idPath;
    @FXML
    private TableColumn<TableRow, Boolean> idDeleteButton;

    // инициализируем форму данными
    @FXML
    private void initialize() {
//        initData();
        setUpTable();
        idRow.setCellValueFactory(new PropertyValueFactory<TableRow, Integer>("id"));
        idIconPath.setCellValueFactory(new PropertyValueFactory<TableRow, String>("iconPath"));
        idPath.setCellValueFactory(new PropertyValueFactory<TableRow, String>("path"));
        idDeleteButton.setCellValueFactory(new PropertyValueFactory<TableRow, Boolean>("check"));
    }

    private void setUpTable() {
        tableUsers.setItems(usersData);
    }

    private void initData() {
        int i = 0;
        usersData.clear();
        for (String folder : FolderContainer.getInstant().getFolders()) {
            i++;
            usersData.add(new TableRow("/image", folder, true, i));
        }
    }

    public void showFoldes() {
        if(FolderContainer.getInstant().getFolders().size() > 0) {
            initData();
            setUpTable();
        }
    }

}


