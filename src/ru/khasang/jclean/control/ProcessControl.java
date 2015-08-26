package ru.khasang.jclean.control;

import ru.khasang.jclean.module.JContainer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ProcessControl {
    private UIControl guiControl = new GUIControl();
    private boolean running;
    private static State state;
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
        guiControl.getSearchInfoFromUser();
        try {
            jContainer.FindAllIdenticalFiles("path");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ArrayList hexIdentical = JContainer.getHexIdentical();
        guiControl.show(hexIdentical);
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
