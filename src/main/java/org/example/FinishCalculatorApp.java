package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishCalculatorApp {

    private JComboBox<String> comboWorkType;
    private JLabel labelArea;
    private JTextField areaInput;
    private JComboBox<String> comboCeilingType; // Новое поле для выбора типа потолка
    private JComboBox<String> comboWallWorkType; // Новое поле для выбора типов работ со стенами
    private JCheckBox checkboxElectrical; // Новое поле для выбора наличия электрики
    private JCheckBox checkboxPlumbing; // Новое поле для выбора наличия сантехники
    private JComboBox<String> comboFloorType; // Новое поле для выбора типа полов
    private JLabel labelOutput;
    private JButton calculateButton;
    private JButton exitButton;

    public FinishCalculatorApp() {
        // Создаем JFrame
        JFrame frame = new JFrame("Калькулятор отделочных работ");
        frame.setSize(400, 300); // Увеличим высоту фрейма для учета новых компонентов
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем JPanel
        JPanel panel = new JPanel();
        frame.add(panel);
        разместитьКомпоненты(panel);

        // Устанавливаем видимость фрейма
        frame.setVisible(true);
    }

    private void разместитьКомпоненты(JPanel panel) {
        panel.setLayout(null);

        JLabel workTypeLabel = new JLabel("Выберите вид работ:");
        workTypeLabel.setBounds(10, 20, 150, 20);
        panel.add(workTypeLabel);

        String[] workTypes = {"Покраска", "Обои", "Напольное покрытие"};
        comboWorkType = new JComboBox<>(workTypes);
        comboWorkType.setBounds(160, 20, 150, 20);
        panel.add(comboWorkType);

        labelArea = new JLabel("Введите площадь (в км2):");
        labelArea.setBounds(10, 50, 200, 20);
        panel.add(labelArea);

        areaInput = new JTextField(10);
        areaInput.setBounds(220, 50, 90, 20);
        panel.add(areaInput);

        JLabel ceilingTypeLabel = new JLabel("Выберите тип потолка:");
        ceilingTypeLabel.setBounds(10, 80, 150, 20);
        panel.add(ceilingTypeLabel);

        String[] ceilingTypes = {"Гипсокартон", "Натяжной потолок", "Покраска"};
        comboCeilingType = new JComboBox<>(ceilingTypes);
        comboCeilingType.setBounds(160, 80, 150, 20);
        panel.add(comboCeilingType);

        JLabel wallWorkTypeLabel = new JLabel("Выберите вид работ со стенами:");
        wallWorkTypeLabel.setBounds(10, 110, 200, 20);
        panel.add(wallWorkTypeLabel);

        String[] wallWorkTypes = {"Штукатурка", "Обои", "Покраска"};
        comboWallWorkType = new JComboBox<>(wallWorkTypes);
        comboWallWorkType.setBounds(220, 110, 90, 20);
        panel.add(comboWallWorkType);

        checkboxElectrical = new JCheckBox("Электрика");
        checkboxElectrical.setBounds(10, 140, 150, 20);
        panel.add(checkboxElectrical);

        checkboxPlumbing = new JCheckBox("Сантехника");
        checkboxPlumbing.setBounds(160, 140, 150, 20);
        panel.add(checkboxPlumbing);

        JLabel floorTypeLabel = new JLabel("Выберите тип полов:");
        floorTypeLabel.setBounds(10, 170, 150, 20);
        panel.add(floorTypeLabel);

        String[] floorTypes = {"Ламинат", "Плитка", "Паркет"};
        comboFloorType = new JComboBox<>(floorTypes);
        comboFloorType.setBounds(160, 170, 150, 20);
        panel.add(comboFloorType);

        calculateButton = new JButton("Рассчитать стоимость");
        calculateButton.setBounds(10, 200, 150, 20);
        calculateButton.addActionListener(new CalculateListener());
        panel.add(calculateButton);

        exitButton = new JButton("Выход");
        exitButton.setBounds(220, 200, 90, 20);
        exitButton.addActionListener(new ExitListener());
        panel.add(exitButton);

        labelOutput = new JLabel("Общая стоимость: ");
        labelOutput.setBounds(10, 230, 200, 20);
        panel.add(labelOutput);
    }

    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedWorkType = (String) comboWorkType.getSelectedItem();
            String areaInputText = areaInput.getText();

            if (isValidInput(areaInputText)) {
                double area = Double.parseDouble(areaInputText);

                String selectedCeilingType = (String) comboCeilingType.getSelectedItem();
                String selectedWallWorkType = (String) comboWallWorkType.getSelectedItem();
                boolean hasElectrical = checkboxElectrical.isSelected();
                boolean hasPlumbing = checkboxPlumbing.isSelected();
                String selectedFloorType = (String) comboFloorType.getSelectedItem();

                double ratePerSquareMeter = 25.0;
                double totalCost = ratePerSquareMeter * area;

                // Учет выбранных параметров для расчета стоимости
                if ("Гипсокартон".equals(selectedCeilingType)) {
                    totalCost += 5.0 * area; // Пример: добавляем 5 рублей за квадратный метр гипсокартона
                }

                if ("Обои".equals(selectedWallWorkType)) {
                    totalCost += 3.0 * area; // Пример: добавляем 3 рубля за квадратный метр работ по обоям
                }

                if (hasElectrical) {
                    totalCost += 1000.0; // Пример: добавляем фиксированную стоимость за электрику
                }

                if (hasPlumbing) {
                    totalCost += 1500.0; // Пример: добавляем фиксированную стоимость за сантехнику
                }

                if ("Ламинат".equals(selectedFloorType)) {
                    totalCost += 8.0 * area; // Пример: добавляем 8 рублей за квадратный метр ламината
                }

                labelOutput.setText("Общая стоимость: " + totalCost + " руб.");
            } else {
                JOptionPane.showMessageDialog(null, "Введите корректное значение для площади.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

        private boolean isValidInput(String input) {
            return !input.isEmpty() && input.matches("^[0-9]+(\\.[0-9]+)?$");
        }
    }


    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinishCalculatorApp());
    }
}
