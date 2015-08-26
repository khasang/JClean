package ru.khasang.jclean.control;

import ru.khasang.jclean.module.JContainer;

import java.util.ArrayList;

public class ProcessControl {
    private boolean running;
    private static State state;
    private UIControl guiControl = new GUIControl();
    JContainer jContainer = new JContainer();
    
    private enum State {
        MAIN_MENU, SEARCH, REPORT
    }

    public void Start() {
        setUp();
        processLoop();
        cleanUp();
    }

    private void setUp() {
        running = true;
        state = State.MAIN_MENU;
    }

    private void processLoop() {
        while (running) {
            windowsStatus();
            logic();
            update();
        }
    }

    private void logic() {
        switch (state) {
            case MAIN_MENU:
                guiControl.getSearchInfoFromWindow();
                break;
            case SEARCH:
                jContainer.FindAllIdenticalFiles("");
                break;
            case REPORT:
                ArrayList hexIdentical = JContainer.getHexIdentical();
                guiControl.show(hexIdentical);
                break;
        }
    }

    private void windowsStatus() {
        switch (state) {
            case MAIN_MENU:
                guiControl.drawWindow("Main");
                break;
            case SEARCH:
                guiControl.drawWindow("Search");
                break;
            case REPORT:
                guiControl.drawWindow("Report");
                break;
        }
    }

    private void update() {

    }

    private void cleanUp() {

    }

}
