package org.example;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



public class FinishCalculatorGUI {
    private JComboBox<String> comboFinishType;
    private JLabel labelArea = new JLabel("Введите площадь для отделочных работ:");
    private JTextField areaInput = new JTextField(10);
    private JLabel labelOutput = new JLabel("Общая стоимость отделочных работ:");
    private JLabel textOutput = new JLabel("");
    private String[] finishTypes = {"Покраска", "Обои", "Напольное покрытие"};
    private JLabel[] arrayLabel = {labelArea};
    private JTextField[] arrayTextField = {areaInput};
    private JFrame finishGUI;
    private JPanel finishPanel;


    public FinishCalculatorGUI(String name, String label) {


        finishGUI = new JFrame("Finish Calculator");
        finishGUI.setTitle(name);
        finishGUI.setBounds(500, 400, 400, 500);
        finishGUI.setResizable(false);

        finishPanel = new JPanel();
        finishPanel.setLayout(null);
        finishGUI.add(finishPanel);

        JLabel infoLabel = new JLabel(label);
        infoLabel.setBounds(60, 0, 300, 30);
        finishPanel.add(infoLabel);

        comboFinishType = new JComboBox<>(finishTypes);
        comboFinishType.setBounds(30, 40, 150, 30);
        finishPanel.add(comboFinishType);

        labelOutput.setBounds(30, 300, 250, 30);
        finishPanel.add(labelOutput);

        textOutput.setBounds(30, 330, 250, 30);
        textOutput.setEnabled(false);
        finishPanel.add(textOutput);


        JButton calculateButton = new JButton("Рассчитать стоимость");
        calculateButton.setBounds(20, 400, 150, 40);

        ActionListener calculateFinishListener = new CalculateFinishListener(comboFinishType, areaInput, textOutput);
        calculateButton.addActionListener(calculateFinishListener);
        finishPanel.add(calculateButton);





        JButton exitButton = new JButton("Выход");
        exitButton.setBounds(270, 400, 100, 40);
        ActionListener exitListener = new ExitFinishListener();
        exitButton.addActionListener(exitListener);
        finishPanel.add(exitButton);

        finishGUI.setVisible(true);
        finishGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public JPanel getPanel() {
        return finishPanel;
    }

    public JLabel[] getLabels() {
        return arrayLabel;
    }

    public JTextField[] getTextFields() {
        return arrayTextField;
    }

    public String[] getFinishTypes() {
        return finishTypes;
    }

    public JLabel getLabelOutput() {
        return textOutput;
    }

    public JComboBox<String> getComboFinishType() {
        return comboFinishType;
    }
}
