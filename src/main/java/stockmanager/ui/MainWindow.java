package stockmanager.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import stockmanager.db.JDBCHistoryDAO;
import stockmanager.domain.Sheriff;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.resources.Corn;
import stockmanager.domain.resources.Wheat;
import stockmanager.domain.stock.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
    public static final String APPLICATION_TITLE = "Sheriff's Office";

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
        setContentPane(mainPanel);
        setTitle(APPLICATION_TITLE);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        cowMaxField.setText(String.valueOf(Warehouse.INSTANCE.getMax(Cow.INSTANCE)));
        sheepMaxField.setText(String.valueOf(Warehouse.INSTANCE.getMax(Sheep.INSTANCE)));
        pigMaxField.setText(String.valueOf(Warehouse.INSTANCE.getMax(Pig.INSTANCE)));

        final List<Long> after = JDBCHistoryDAO.INSTANCE.getLastButOneAfterValues();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cowStockField.getText().isEmpty() || sheepStockField.getText().isEmpty() || pigStockField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "All fields must be set",
                            "Invalid input",
                            JOptionPane.ERROR_MESSAGE);
                } else if ((Integer.parseInt(cowStockField.getText()) > after.get(0)) || (Integer.parseInt(sheepStockField.getText()) > after.get(1)) || (Integer.parseInt(pigStockField.getText()) > after.get(2))) {
                    JOptionPane.showMessageDialog(null,
                            "At least one of the stock values are greater than the actual values in the warehouse.",
                            "Invalid input",
                            JOptionPane.ERROR_MESSAGE);

                } else if ((Integer.parseInt(cowStockField.getText()) < 0) || (Integer.parseInt(sheepStockField.getText()) < 0) || (Integer.parseInt(pigStockField.getText()) < 0) || (Long.parseLong(wheatField.getText()) < 0) || (Long.parseLong(cornField.getText()) < 0)) {
                    JOptionPane.showMessageDialog(null,
                            "Negative values are not allowed.",
                            "Invalid input",
                            JOptionPane.ERROR_MESSAGE);
                } else {
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

            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("center:max(d;4px):grow,left:4dlu:noGrow,center:max(d;4px):grow,left:4dlu:noGrow,center:max(d;4px):grow,left:4dlu:noGrow,center:max(d;4px):grow,left:5px:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):grow", "center:50px:noGrow,top:4dlu:noGrow,center:min(m;100px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.setEnabled(true);
        mainPanel.setMinimumSize(new Dimension(800, 260));
        mainPanel.setName("Animal Breeding");
        mainPanel.setPreferredSize(new Dimension(800, 260));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 14));
        label1.setText("Cow");
        CellConstraints cc = new CellConstraints();
        mainPanel.add(label1, cc.xy(3, 1));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 14));
        label2.setText("Sheep");
        mainPanel.add(label2, cc.xy(5, 1));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), label3.getFont().getStyle(), 14));
        label3.setText("Pig");
        mainPanel.add(label3, cc.xy(7, 1));
        final JLabel label4 = new JLabel();
        label4.setText("StockMax");
        mainPanel.add(label4, cc.xy(1, 3));
        final JLabel label5 = new JLabel();
        label5.setText("Stock");
        mainPanel.add(label5, cc.xy(1, 5));
        final JLabel label6 = new JLabel();
        label6.setText("Sold");
        mainPanel.add(label6, cc.xy(1, 7));
        final JLabel label7 = new JLabel();
        label7.setText("Priority");
        mainPanel.add(label7, cc.xy(1, 9));
        final JLabel label8 = new JLabel();
        label8.setText("Produced");
        mainPanel.add(label8, cc.xy(1, 11));
        final JLabel label9 = new JLabel();
        label9.setText("NewStock");
        mainPanel.add(label9, cc.xy(1, 13));
        cowPriorTextField = new JTextField();
        cowPriorTextField.setColumns(5);
        cowPriorTextField.setEditable(false);
        cowPriorTextField.setEnabled(true);
        cowPriorTextField.setFocusable(false);
        cowPriorTextField.setHorizontalAlignment(0);
        mainPanel.add(cowPriorTextField, cc.xy(3, 9, CellConstraints.CENTER, CellConstraints.DEFAULT));
        sheepPriorTextField = new JTextField();
        sheepPriorTextField.setColumns(5);
        sheepPriorTextField.setEditable(false);
        sheepPriorTextField.setEnabled(true);
        sheepPriorTextField.setFocusable(false);
        sheepPriorTextField.setHorizontalAlignment(0);
        mainPanel.add(sheepPriorTextField, cc.xy(5, 9, CellConstraints.CENTER, CellConstraints.DEFAULT));
        pigPriorTextField = new JTextField();
        pigPriorTextField.setColumns(5);
        pigPriorTextField.setEditable(false);
        pigPriorTextField.setEnabled(true);
        pigPriorTextField.setFocusable(false);
        pigPriorTextField.setHorizontalAlignment(0);
        mainPanel.add(pigPriorTextField, cc.xy(7, 9, CellConstraints.CENTER, CellConstraints.DEFAULT));
        cowMaxField = new JTextField();
        cowMaxField.setColumns(5);
        cowMaxField.setEditable(false);
        cowMaxField.setEnabled(true);
        cowMaxField.setFocusable(false);
        cowMaxField.setHorizontalAlignment(0);
        mainPanel.add(cowMaxField, cc.xy(3, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        sheepMaxField = new JTextField();
        sheepMaxField.setColumns(5);
        sheepMaxField.setEditable(false);
        sheepMaxField.setEnabled(true);
        sheepMaxField.setFocusable(false);
        sheepMaxField.setHorizontalAlignment(0);
        mainPanel.add(sheepMaxField, cc.xy(5, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        pigMaxField = new JTextField();
        pigMaxField.setColumns(5);
        pigMaxField.setEditable(false);
        pigMaxField.setEnabled(true);
        pigMaxField.setFocusable(false);
        pigMaxField.setHorizontalAlignment(0);
        mainPanel.add(pigMaxField, cc.xy(7, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        cowStockField = new JTextField();
        cowStockField.setColumns(5);
        cowStockField.setDragEnabled(false);
        cowStockField.setEditable(true);
        cowStockField.setEnabled(true);
        cowStockField.setHorizontalAlignment(0);
        cowStockField.setText("");
        mainPanel.add(cowStockField, cc.xy(3, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        sheepStockField = new JTextField();
        sheepStockField.setColumns(5);
        sheepStockField.setEditable(true);
        sheepStockField.setEnabled(true);
        sheepStockField.setHorizontalAlignment(0);
        sheepStockField.setText("");
        mainPanel.add(sheepStockField, cc.xy(5, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        pigStockField = new JTextField();
        pigStockField.setColumns(5);
        pigStockField.setEditable(true);
        pigStockField.setEnabled(true);
        pigStockField.setHorizontalAlignment(0);
        mainPanel.add(pigStockField, cc.xy(7, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        cowSoldField = new JTextField();
        cowSoldField.setColumns(5);
        cowSoldField.setEditable(false);
        cowSoldField.setEnabled(true);
        cowSoldField.setFocusable(false);
        cowSoldField.setHorizontalAlignment(0);
        mainPanel.add(cowSoldField, cc.xy(3, 7, CellConstraints.CENTER, CellConstraints.DEFAULT));
        sheepSoldField = new JTextField();
        sheepSoldField.setColumns(5);
        sheepSoldField.setEditable(false);
        sheepSoldField.setEnabled(true);
        sheepSoldField.setFocusable(false);
        sheepSoldField.setHorizontalAlignment(0);
        mainPanel.add(sheepSoldField, cc.xy(5, 7, CellConstraints.CENTER, CellConstraints.DEFAULT));
        pigSoldField = new JTextField();
        pigSoldField.setColumns(5);
        pigSoldField.setEditable(false);
        pigSoldField.setEnabled(true);
        pigSoldField.setFocusable(false);
        pigSoldField.setHorizontalAlignment(0);
        mainPanel.add(pigSoldField, cc.xy(7, 7, CellConstraints.CENTER, CellConstraints.DEFAULT));
        cowProducedField = new JTextField();
        cowProducedField.setColumns(5);
        cowProducedField.setEditable(false);
        cowProducedField.setEnabled(true);
        cowProducedField.setFocusable(false);
        cowProducedField.setHorizontalAlignment(0);
        mainPanel.add(cowProducedField, cc.xy(3, 11, CellConstraints.CENTER, CellConstraints.DEFAULT));
        sheepProducedField = new JTextField();
        sheepProducedField.setColumns(5);
        sheepProducedField.setEditable(false);
        sheepProducedField.setEnabled(true);
        sheepProducedField.setFocusable(false);
        sheepProducedField.setHorizontalAlignment(0);
        mainPanel.add(sheepProducedField, cc.xy(5, 11, CellConstraints.CENTER, CellConstraints.DEFAULT));
        pigProducedField = new JTextField();
        pigProducedField.setColumns(5);
        pigProducedField.setEditable(false);
        pigProducedField.setEnabled(true);
        pigProducedField.setFocusable(false);
        pigProducedField.setHorizontalAlignment(0);
        mainPanel.add(pigProducedField, cc.xy(7, 11, CellConstraints.CENTER, CellConstraints.DEFAULT));
        cowNewStockField = new JTextField();
        cowNewStockField.setColumns(5);
        cowNewStockField.setEditable(false);
        cowNewStockField.setEnabled(true);
        cowNewStockField.setFocusable(false);
        cowNewStockField.setHorizontalAlignment(0);
        mainPanel.add(cowNewStockField, cc.xy(3, 13, CellConstraints.CENTER, CellConstraints.DEFAULT));
        sheepNewStockField = new JTextField();
        sheepNewStockField.setColumns(5);
        sheepNewStockField.setEditable(false);
        sheepNewStockField.setEnabled(true);
        sheepNewStockField.setFocusable(false);
        sheepNewStockField.setHorizontalAlignment(0);
        mainPanel.add(sheepNewStockField, cc.xy(5, 13, CellConstraints.CENTER, CellConstraints.DEFAULT));
        pigNewStockField = new JTextField();
        pigNewStockField.setColumns(5);
        pigNewStockField.setEditable(false);
        pigNewStockField.setEnabled(true);
        pigNewStockField.setFocusable(false);
        pigNewStockField.setHorizontalAlignment(0);
        mainPanel.add(pigNewStockField, cc.xy(7, 13, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JLabel label10 = new JLabel();
        label10.setFont(new Font(label10.getFont().getName(), label10.getFont().getStyle(), 14));
        label10.setText("Resources");
        mainPanel.add(label10, cc.xyw(9, 1, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JLabel label11 = new JLabel();
        label11.setText("Corn");
        mainPanel.add(label11, cc.xy(11, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JLabel label12 = new JLabel();
        label12.setText("Wheat");
        mainPanel.add(label12, cc.xy(9, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
        wheatField = new JTextField();
        wheatField.setHorizontalAlignment(0);
        wheatField.setMinimumSize(new Dimension(6, 22));
        wheatField.setPreferredSize(new Dimension(61, 22));
        mainPanel.add(wheatField, cc.xy(9, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        cornField = new JTextField();
        cornField.setHorizontalAlignment(0);
        cornField.setPreferredSize(new Dimension(61, 22));
        mainPanel.add(cornField, cc.xy(11, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        calculateButton = new JButton();
        calculateButton.setBorderPainted(true);
        calculateButton.setContentAreaFilled(true);
        calculateButton.setDefaultCapable(true);
        calculateButton.setDoubleBuffered(false);
        calculateButton.setEnabled(true);
        calculateButton.setHorizontalTextPosition(0);
        calculateButton.setText("Calculate");
        calculateButton.setMnemonic('C');
        calculateButton.setDisplayedMnemonicIndex(0);
        mainPanel.add(calculateButton, cc.xy(13, 15, CellConstraints.CENTER, CellConstraints.DEFAULT));
        label7.setLabelFor(cowPriorTextField);
        label11.setLabelFor(cornField);
        label12.setLabelFor(wheatField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
