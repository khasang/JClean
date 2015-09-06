package ru.khasang.jclean.view;

/**
 * Created by Hank on 24.08.2015.
 */
public class ResultWindow  extends JCWindow {
    CommunicationsProtocol communicationsProtocol = new CommunicationsProtocol();
    @Override
    public void create() {

    }

    @Override
    public CommunicationsProtocol report() {
        return communicationsProtocol;
    }
}
