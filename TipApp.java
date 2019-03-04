/***
 * Group member names: Gopala Sai Uppalapati (Z1840615),Sneha Konatham (Z1838982), Anusha Chanduri(Z1840609)
 * Assignment-2
 * CSCI 502
 *****/
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TipApp extends JFrame implements ActionListener, ChangeListener {

    /**
     * TipApp data members
     * billAmount textfield
     * tipPercentageSlider Jslider
     * partySizeSpinner JSpinner
     * calculateButton JButton
     * clearButton JButton
     * totalBillLabel JLabel
     * individualShareLablel Jlabel
     * **/
    private static final long serialVersionUID = 1L;
    //variable for Tipcalculator class
    TipCalculator tc = new TipCalculator();
    private JTextField billAmountField = new JTextField(10);
    private JSlider tipPercentageSlider=new JSlider(0,50,20);
    private JSpinner partySizeSpinner=new JSpinner(new SpinnerNumberModel(1,1,20,1));
    private JButton calculateButton = new JButton("Calculate");
    private JButton clearButton = new JButton("Clear Numbers");
    private JLabel totalBillLabel = new JLabel();
    private JLabel individualShareLabel=new JLabel();
    /**
     * Declate Title for GUI
     * */
    private TipApp(String title) {
        super(title);
    }
    /**
     * createAndShowGUI method which which calls initComponents method and initializes change and action listners
     * **/
    private void createAndShowGUI() {
        initComponents();
        // Add listeners for the buttons and changelistner for buttons
        tipPercentageSlider.addChangeListener(this);
        partySizeSpinner.addChangeListener(this);
        calculateButton.addActionListener(this);
        clearButton.addActionListener(this);
        // Display the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private void initComponents(){
        /***
         * initialize screen components and design GUI
         * */
        //mainPanel -Gridlayout(5X1)
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //initialize nested boxLayout1
        JPanel boxLayout1= new JPanel();
        boxLayout1.setLayout(new BoxLayout(boxLayout1,BoxLayout.X_AXIS));
        boxLayout1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        boxLayout1.add(Box.createHorizontalGlue());
        //adding bill amount label and textfield for billAmount
        boxLayout1.add(new JLabel("Bill Amount:"));
        boxLayout1.add(Box.createRigidArea(new Dimension(10, 0)));
        boxLayout1.add(billAmountField);
        //adding nested box layout-1 to mainpanel
        mainPanel.add(boxLayout1);
        //initialize nested boxLayout2
        JPanel boxLayout2=new JPanel();
        boxLayout2.setLayout(new BoxLayout(boxLayout2,BoxLayout.X_AXIS));
        boxLayout2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        //adding Tip Percentage label and tipPercentage JSlider
        boxLayout2.add(new JLabel("Tip percentage:"));
        boxLayout2.add(Box.createRigidArea(new Dimension(10,0)));
        boxLayout2.add(tipPercentageSlider);
        tipPercentageSlider.setMinorTickSpacing(1);
        tipPercentageSlider.setMajorTickSpacing(10);
        tipPercentageSlider.setPaintTicks(true);
        tipPercentageSlider.setPaintLabels(true);
        //adding nested box layout-2 to mainpanel
        mainPanel.add(boxLayout2);
        //initialize nested boxLayout3
        JPanel boxLayout3=new JPanel();
        boxLayout3.setLayout(new BoxLayout(boxLayout3,BoxLayout.X_AXIS));
        boxLayout3.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        //adding Party size label and partySize JSpinner
        boxLayout3.add(new JLabel("Party Size:"));
        boxLayout3.add(Box.createRigidArea(new Dimension(10,0)));
        boxLayout3.add(partySizeSpinner);
        //disable textfield input for Jspinner
        ((JSpinner.DefaultEditor) partySizeSpinner.getEditor()).getTextField().setEditable(false);
        //adding nested box layout-3 to mainpanel
        mainPanel.add(boxLayout3);
        //initialize nested flowLayout
        JPanel flowLayout=new JPanel();
        flowLayout.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        //adding calculateButton and clearButton
        flowLayout.add(calculateButton);
        flowLayout.add(clearButton);
        //add flow layout to mainppanel
        mainPanel.add(flowLayout);
        //nested grid layout(2X2) to display total bill and individual share
        JPanel gridLayout = new JPanel(new GridLayout(2, 2, 5, 5));
        gridLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridLayout.add(new JLabel("Total Bill(with Tip):"));
        gridLayout.add(totalBillLabel);
        gridLayout.add(new JLabel("Individual share:"));
        gridLayout.add(individualShareLabel);
        totalBillLabel.setText("$ 0.00");
        individualShareLabel.setText("$ 0.00");
        //add grid layout to mainPanel
        mainPanel.add(gridLayout);
        add(mainPanel, BorderLayout.CENTER);

    }
    /***
     * changeevent listner for tipPercentageSlider and partySizeSpinner
     * **/
    @Override
    public void stateChanged(ChangeEvent ce) {
        //fetch from changeevent setvalue for TipCalculator class
        tc.setTipPercentage(tipPercentageSlider.getValue());
        tc.setPartySize((int)partySizeSpinner.getValue());

    }
    /***
     * Action llistner to calculate and error checking
     * **/
    @Override
    public void actionPerformed(ActionEvent ae) {
        double billamttodouble=0;
        //calculate button when clicked
        if(ae.getSource()==calculateButton){
            try{
                billamttodouble=Double.parseDouble(billAmountField.getText());
                if(billamttodouble>0) {
                    tc.setBillAmount(billamttodouble);
                    totalBillLabel.setText("$"+String.format("%.2f",tc.getTotalBill()));
                    individualShareLabel.setText("$"+String.format("%.2f",tc.getIndividualShare()));
                }
                else if(billamttodouble<=0){
                    //dialogbox if bill amoount is less than or equal to zero
                    JOptionPane.showMessageDialog(null, "Bill amount must be greater than 0");
                }

            }
            catch(NumberFormatException nfe){
                //dialogbox if bill amoount is not a number
                JOptionPane.showMessageDialog(null, "Bill amount must be numeric");
            }

        }
        //clear button clicked
        else if(ae.getSource()==clearButton){
            System.out.println("working");
            billAmountField.setText("");
            partySizeSpinner.setValue(1);
            tipPercentageSlider.setValue(20);
            totalBillLabel.setText("$ 0.00");
            individualShareLabel.setText("$ 0.00");
        }
    }
    //main method
    public static void main(String[] args) {
        //change GUI Metallook
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException
                | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        //invoke later lamda expression
        EventQueue.invokeLater(() -> {
            //TipApp class local variable
            TipApp mainFrame = new TipApp("Tip Calculator");
            mainFrame.createAndShowGUI();
        });

    }



}
