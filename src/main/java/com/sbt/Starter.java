package com.sbt;

import com.sbt.model.CalculationImpl;
import com.sbt.view.CalculatorFrame;

import javax.swing.*;
import java.awt.*;


public class Starter {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CalculatorFrame frame = new CalculatorFrame(new CalculationImpl());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
