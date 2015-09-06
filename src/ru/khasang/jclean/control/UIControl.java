package ru.khasang.jclean.control;

import ru.khasang.jclean.view.CommunicationsProtocol;

import java.util.ArrayList;

public abstract class UIControl {

    public abstract void drawWindow(String windowName);

    public abstract CommunicationsProtocol getReportFromWindow(String windowName);

    public abstract void show(ArrayList hexIdentical);
}
