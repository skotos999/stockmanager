package stockmanager.ui;

import stockmanager.db.JDBCHistoryDAO;
import stockmanager.domain.Sheriff;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.stock.Warehouse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public static final String APPLICATION_TITLE = "Sheriff's Office";
    public static final String MAIN_WINDOW_NAME = "Animal Breeding";
    public static final String CALCULATE_BUTTON_NAME = "calculateButton";

    private JPanel mainPanel;
    private JTextField cowPriorTextField;
    private JTextField sheepPriorTextField;
    private JTextField pigPriorTextField;
    private JTextField cowMaxField;
    private JTextField sheepMaxField;
    private JTextField pigMaxField;
    private JTextField cowStockField;
    private JTextField sheepStockField;
    private JTextField pigStockField;
    private JTextField cowSoldField;
    private JTextField sheepSoldField;
    private JTextField pigSoldField;
    private JTextField cowProducedField;
    private JTextField sheepProducedField;
    private JTextField pigProducedField;
    private JTextField cowNewStockField;
    private JTextField sheepNewStockField;
    private JTextField pigNewStockField;
    private JTextField wheatField;
    private JTextField cornField;
    private JButton calculateButton;

    private Sheriff sheriff = new Sheriff();

    public MainWindow() {
        setName(MainWindow.MAIN_WINDOW_NAME);
        setContentPane(mainPanel);
        setTitle(APPLICATION_TITLE);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        cowMaxField.setText(String.valueOf(Warehouse.INSTANCE.getMax(Cow.INSTANCE)));
        sheepMaxField.setText(String.valueOf(Warehouse.INSTANCE.getMax(Sheep.INSTANCE)));
        pigMaxField.setText(String.valueOf(Warehouse.INSTANCE.getMax(Pig.INSTANCE)));

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer[] beforeValues = new Integer[]{
                        Integer.parseInt(cowStockField.getText()),
                        Integer.parseInt(sheepStockField.getText()),
                        Integer.parseInt(pigStockField.getText())
                };

                Warehouse.INSTANCE.add(Wheat.INSTANCE, Long.parseLong(wheatField.getText()));
                Warehouse.INSTANCE.add(Corn.INSTANCE, Long.parseLong(cornField.getText()));

                JDBCHistoryDAO.INSTANCE.setBeforeValuesInDatabase(beforeValues);

                Long[] soldValues = JDBCHistoryDAO.INSTANCE.calculateSoldValues();
                cowSoldField.setText(String.valueOf(soldValues[0]));
                sheepSoldField.setText(String.valueOf(soldValues[1]));
                pigSoldField.setText(String.valueOf(soldValues[2]));

                sheriff.startProduction();

                JDBCHistoryDAO.INSTANCE.setAfterValuesInDatabase();

                cowPriorTextField.setText(String.valueOf(Cow.INSTANCE.getPriority()));
                sheepPriorTextField.setText(String.valueOf(Sheep.INSTANCE.getPriority()));
                pigPriorTextField.setText(String.valueOf(Pig.INSTANCE.getPriority()));

                cowProducedField.setText(String.valueOf(Warehouse.INSTANCE.getQty(Cow.INSTANCE) - beforeValues[0]));
                sheepProducedField.setText(String.valueOf(Warehouse.INSTANCE.getQty(Sheep.INSTANCE) - beforeValues[1]));
                pigProducedField.setText(String.valueOf(Warehouse.INSTANCE.getQty(Pig.INSTANCE) - beforeValues[2]));

                cowNewStockField.setText(String.valueOf(Warehouse.INSTANCE.getQty(Cow.INSTANCE)));
                sheepNewStockField.setText(String.valueOf(Warehouse.INSTANCE.getQty(Sheep.INSTANCE)));
                pigNewStockField.setText(String.valueOf(Warehouse.INSTANCE.getQty(Pig.INSTANCE)));
            }
        });
    }
}
