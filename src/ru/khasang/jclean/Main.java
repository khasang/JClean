package ru.khasang.jclean;

import ru.khasang.jclean.control.ProcessControl;

/**
 * Created by Hank on 24.08.2015.
 */
public class Main {
    public static void main(String[] args) {
        ProcessControl processControl = new ProcessControl();
        processControl.Start();
    }
}
