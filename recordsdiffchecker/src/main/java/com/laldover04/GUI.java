package com.laldover04;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI implements ActionListener {
    private final int windowWidth = 1000;
    private final int windowHeight = 700;

    private final JFrame frame;
    private final JPanel panel;

    private final JLabel resultLabel;
    private final JLabel resultTitleLabel;
    private final JLabel titleLabel;
    private final JLabel transactionLookupLabel;
    private final JLabel spaInputLabel;
    private final JLabel serviceCodeInputLabel;

    private final JButton resetButton;
    private final JButton searchButton;

    private final JTextField spaInput;
    private final JTextField serviceCodeInput;

    private final XLSXreader tarReader;
    private final XLSXreader ecbReader;


    public GUI() {
        //Setup XLSX to be used by the buttons
		tarReader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        ecbReader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
		
        // Initial setup
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        //
        panel.setLayout(null);


        // Title
        titleLabel = new JLabel("Ship Keep Co Records");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setBounds(windowWidth / 3, 0, 200, 25);
        panel.add(titleLabel);


        // Search Area Description
        transactionLookupLabel = new JLabel("Transaction Record Lookup");
        transactionLookupLabel.setBounds(windowWidth / 10 - 17, 50, 200, 25);
        transactionLookupLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(transactionLookupLabel);


        // SPA label
        spaInputLabel = new JLabel("SPA");
        spaInputLabel.setBounds(windowWidth / 10, 90, 300, 25);
        panel.add(spaInputLabel);


        // SPA input
        spaInput = new JTextField(20);
        spaInput.setBounds(windowWidth / 10, 115, 165, 25);
        panel.add(spaInput);


        // Service Code label
        serviceCodeInputLabel = new JLabel("Service Code");
        serviceCodeInputLabel.setBounds(windowWidth / 10, 150, 300, 25);
        panel.add(serviceCodeInputLabel);


        // Service Code input
        serviceCodeInput = new JTextField(20);
        serviceCodeInput.setBounds(windowWidth / 10, 175, 165, 25);
        panel.add(serviceCodeInput);


        // Search Button
        searchButton = new JButton("search");
        searchButton.setBounds(windowWidth / 10 + 85, 210, 80, 25);
        searchButton.addActionListener(this);
        panel.add(searchButton);

        // Reset Button
        resetButton = new JButton("reset");
        resetButton.setBounds(windowWidth / 10, 210, 80, 25);
        resetButton.addActionListener(this);
        panel.add(resetButton);

        // Lookup Results label
        resultTitleLabel = new JLabel("SPA      Service Code     Record Desc     Charge     New Charge");
        resultTitleLabel.setBounds(windowWidth / 5 * 2, 85, 500, 25);
        panel.add(resultTitleLabel);

        // Lookup Results label
        resultLabel = new JLabel("search results");
        resultLabel.setBounds(windowWidth / 5 * 2 -20, 115, 500, 25);
        panel.add(resultLabel);


        frame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {


        // resets resultLabel and inputs on click
        if (e.getSource() == resetButton) {
            spaInput.setText("");
            serviceCodeInput.setText("");
            resultLabel.setText("");
        } else if (e.getSource() == searchButton) {
            // sets result label to the input fields will have to be changed to use a
            // XLSXReader object to look up the row information.
            String[] tarRow = tarReader.getRow(spaInput.getText() + serviceCodeInput.getText());
            String[] ecbRow = ecbReader.getRow(spaInput.getText() + serviceCodeInput.getText());
            String searchResult = "";
            if(tarRow.length == 1){
                searchResult = tarRow[0];
            } else {
                searchResult = (tarRow[0] + "   " + tarRow[1] + "          " +  ecbRow[2] + "    " +  tarRow[2] + "       " +  tarRow[4]);
            }
            resultLabel.setText(searchResult);  //XLSXreader.getRow()
        }
    }


    public static void main(String[] args) {
        new GUI();

    }

};
