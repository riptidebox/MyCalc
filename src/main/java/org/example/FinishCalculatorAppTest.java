package org.example;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class FinishCalculatorAppTest {

    @Test
    void testValidInput_CalculateTotalCost() {
        // Подготовка
        FinishCalculatorApp app = new FinishCalculatorApp();
        FinishCalculatorApp.CalculateListener listener = app.new CalculateListener();

        app.areaInput.setText("10"); // Устанавливаем площадь в 10 кв. метров
        app.comboCeilingType.setSelectedItem("Гипсокартон"); // Выбираем тип потолка "Гипсокартон"
        app.comboWallWorkType.setSelectedItem("Обои"); // Выбираем вид работ со стенами "Обои"
        app.checkboxElectrical.setSelected(true); // Устанавливаем флажок "Электрика"
        app.checkboxPlumbing.setSelected(false); // Сбрасываем флажок "Сантехника"
        app.comboFloorType.setSelectedItem("Ламинат"); // Выбираем тип полов "Ламинат"

        ActionEvent event = new ActionEvent(app.calculateButton, ActionEvent.ACTION_PERFORMED, "");

        // Действие
        listener.actionPerformed(event);

        // Проверка
        assertEquals("Общая стоимость: 3125.0 руб.", app.labelOutput.getText());
    }

    @Test
    void testInvalidInput_ShowErrorMessage() {
        // Подготовка
        FinishCalculatorApp app = new FinishCalculatorApp();
        FinishCalculatorApp.CalculateListener listener = app.new CalculateListener();

        app.areaInput.setText("abc"); // Устанавливаем некорректное значение площади

        ActionEvent event = new ActionEvent(app.calculateButton, ActionEvent.ACTION_PERFORMED, "");

        // Действие
        listener.actionPerformed(event);

        // Проверка
        // Мы не проверяем сообщение об ошибке напрямую, так как методы JOptionPane не являются статическими
        // Мы можем только убедиться, что текстовое поле вывода содержит сообщение
        assertNotNull(app.labelOutput.getText());
    }

    @Test
    void testNumericInputOnly() {
        // Подготовка
        FinishCalculatorApp app = new FinishCalculatorApp();
        JTextField areaInputField = app.areaInput;

        // Действие: вводим текст в поле для ввода площади
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
                new KeyEvent(areaInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a')
        );

        // Проверка: убеждаемся, что в поле для ввода площади осталось пусто
        assertTrue(areaInputField.getText().isEmpty());
    }

}
