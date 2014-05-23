package stockmanager;

import stockmanager.ui.MainWindow;

import javax.swing.*;

public class Main {

    public Main() throws Exception {
        startUserInterface();
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

    private void startUserInterface() throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
    }
}
