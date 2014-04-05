package stockmanager.ui;

import javax.swing.*;

public class MainWindow extends JFrame {
    public static final String APPLICATION_TITLE = "Sheriff's Office";
    public static final String MAIN_WINDOW_NAME = "Animal Breeding";
    public static final String CALCULATE_BUTTON_NAME = "calculateButton";
    private JButton calculateButton;
    private JPanel mainPanel;
    private JTextField cowPrioTextField;
    private JTextField sheepPrioTextField;
    private JTextField pigPrioTextField;

    public MainWindow() {
        setName(MainWindow.MAIN_WINDOW_NAME);
        setContentPane(mainPanel);
        setTitle(APPLICATION_TITLE);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
