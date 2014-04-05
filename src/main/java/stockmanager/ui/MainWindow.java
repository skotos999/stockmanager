package stockmanager.ui;

import javax.swing.*;

public class MainWindow  extends JFrame {
    public static final String APPLICATION_TITLE = "Sheriff's Office";
    public static final String MAIN_WINDOW_NAME = "Animal Breeding";
    private JTextField textField1;
    private JButton calculateButton;

    public MainWindow(){
        setName(MainWindow.MAIN_WINDOW_NAME);
        setTitle(APPLICATION_TITLE);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
