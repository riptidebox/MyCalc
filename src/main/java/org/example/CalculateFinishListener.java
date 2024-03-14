package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateFinishListener implements ActionListener {
    private JComboBox<String> comboFinishType;
    private JTextField areaInput;
    private JLabel textOutput;

    public CalculateFinishListener(JComboBox<String> comboFinishType, JTextField areaInput, JLabel textOutput) {
        this.comboFinishType = comboFinishType;
        this.areaInput = areaInput;
        this.textOutput = textOutput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedFinishType = (String) comboFinishType.getSelectedItem();
        String areaInputText = areaInput.getText();

        if (isValidInput(areaInputText)) {
            double area = Double.parseDouble(areaInputText);

            double ratePerSquareMeter = 25.0; // Подстраивайте это значение в соответствии с реальными ставками

            double totalCost = ratePerSquareMeter * area;

            textOutput.setText("Общая стоимость: " + totalCost + " руб.");
        } else {
            JOptionPane.showMessageDialog(null, "Введите корректное значение для площади.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidInput(String input) {
        return !input.isEmpty() && input.matches("^[0-9]+(\\.[0-9]+)?$");
    }
}
