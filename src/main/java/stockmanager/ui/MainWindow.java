package stockmanager.ui;

import stockmanager.domain.Sheriff;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Sheep;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public static final String APPLICATION_TITLE = "Sheriff's Office";
    public static final String MAIN_WINDOW_NAME = "Animal Breeding";
    public static final String CALCULATE_BUTTON_NAME = "calculateButton";
    private JButton calculateButton;
    private JPanel mainPanel;
    private JTextField cowPriorTextField;
    private JTextField sheepPriorTextField;
    private JTextField pigPriorTextField;

    public MainWindow() {
        setName(MainWindow.MAIN_WINDOW_NAME);
        setContentPane(mainPanel);
        setTitle(APPLICATION_TITLE);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sheriff sheriff = new Sheriff();
                sheriff.startProduction();
                cowPriorTextField.setText(String.valueOf(Cow.INSTANCE.getPriority()));
                sheepPriorTextField.setText(String.valueOf(Sheep.INSTANCE.getPriority()));
                pigPriorTextField.setText(String.valueOf(Pig.INSTANCE.getPriority()));
            }
        });
    }
}
