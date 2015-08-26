package ru.khasang.jclean.control;

import ru.khasang.jclean.view.MainWindow;
import ru.khasang.jclean.view.ResultWindow;
import java.util.ArrayList;

public class GUIControl extends UIControl {
    MainWindow mainWindow;
    ResultWindow resultWindow;

    @Override
    public void drawWindow(String windowName) {
        switch (windowName.toLowerCase()) {
            case "main":
                if(mainWindow != null) {
                    mainWindow.create();
                }
                break;
            case "report":
                if(resultWindow != null) {
                    resultWindow.create();
                }
                break;
        }
    }

    @Override
    public void getSearchInfoFromUser() {

    }

    @Override
    public void show(ArrayList hexIdentical) {

    }
}
