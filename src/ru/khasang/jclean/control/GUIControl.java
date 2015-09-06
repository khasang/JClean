package ru.khasang.jclean.control;

import ru.khasang.jclean.view.CommunicationsProtocol;
import ru.khasang.jclean.view.MainWindow;
import ru.khasang.jclean.view.ResultWindow;
import java.util.ArrayList;

public class GUIControl extends UIControl {
    MainWindow mainWindow = null;
    ResultWindow resultWindow = null;

    @Override
    public void drawWindow(String windowName) {
        switch (windowName.toLowerCase()) {
            case "main":
                if(mainWindow != null) {
                    mainWindow = new MainWindow();
                    mainWindow.create();
                }
                break;
            case "report":
                if(resultWindow != null) {
                    resultWindow = new ResultWindow();
                    resultWindow.create();
                }
                break;
        }
    }

    @Override
    public CommunicationsProtocol getReportFromWindow(String windowName) {
        CommunicationsProtocol report = null;
        switch (windowName.toLowerCase()) {
            case "main":
                if(mainWindow != null) {
                   report = mainWindow.report();
                }
                break;
            case "report":
                if(resultWindow != null) {
                    report = resultWindow.report();
                }
                break;
        }
        return report;
    }

    @Override
    public void show(ArrayList hexIdentical) {

    }
}
