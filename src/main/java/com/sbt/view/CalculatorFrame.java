package com.sbt.view;

import com.sbt.controller.ACAction;
import com.sbt.controller.Action;
import com.sbt.controller.CalculateAction;
import com.sbt.controller.DeleteAction;
import com.sbt.model.Calculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class CalculatorFrame extends JFrame implements CalculatorView {

    private GridBagLayout gbl;
    private JTextField jTextField;
    private final Action baseAction;

    public CalculatorFrame(Calculation calculation) {
        setTitle("Calculator");
        URL resURL = getClass().getResource("/icon.png");
        Image image = new ImageIcon(resURL).getImage();
        setIconImage(image);
        setCoordinates();
        baseAction = new Action(CalculatorFrame.this, calculation) {
            @Override
            public void processAction() {
                throw new UnsupportedOperationException();
            }
        };
        createPane();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveCoordinates();
            }
        });
        pack();
    }

    private void createPane() {
        gbl = new GridBagLayout();
        Container panel = getContentPane();
        panel.setLayout(gbl);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        jTextField = new JTextField("");
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        gbl.setConstraints(jTextField, constraints);
        panel.add(jTextField);

        constraints.gridwidth = 1;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 1;
        ActionListener insert = e -> {
            String input = e.getActionCommand();
            jTextField.setText(jTextField.getText() + input);
        };
        ActionListener del = e -> new DeleteAction(baseAction.getView(), baseAction.getCalculation()).processAction();
        ActionListener ac = e -> new ACAction(baseAction.getView(), baseAction.getCalculation()).processAction();
        ActionListener calculation = e -> new CalculateAction(baseAction.getView(), baseAction.getCalculation()).processAction();

        addButton("ac", ac, constraints, panel);
        addButton("(", insert, constraints, panel);
        addButton(")", insert, constraints, panel);
        addButton("/", insert, constraints, panel);

        constraints.gridy = 2;
        addButton("7", insert, constraints, panel);
        addButton("8", insert, constraints, panel);
        addButton("9", insert, constraints, panel);
        addButton("*", insert, constraints, panel);

        constraints.gridy = 3;
        addButton("4", insert, constraints, panel);
        addButton("5", insert, constraints, panel);
        addButton("6", insert, constraints, panel);
        addButton("-", insert, constraints, panel);

        constraints.gridy = 4;
        addButton("1", insert, constraints, panel);
        addButton("2", insert, constraints, panel);
        addButton("3", insert, constraints, panel);
        addButton("+", insert, constraints, panel);

        constraints.gridy = 5;
        constraints.gridwidth = 2;
        addButton("0", insert, constraints, panel);
        constraints.gridwidth = 1;
        addButton(".", insert, constraints, panel);
        constraints.gridheight = 2;
        addButton("=", calculation, constraints, panel);

        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        constraints.gridy = 6;
        constraints.gridx = 0;
        addButton("del", del, constraints, panel);
    }

    private void addButton(String label, ActionListener listener, GridBagConstraints constraints, Container container) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        gbl.setConstraints(button, constraints);
        container.add(button);
    }


    private void setCoordinates() {
        File file = new File("Coordinates");
        if (!file.exists() || file.isDirectory()) return;
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter(",");
            int x = Integer.parseInt(scanner.next());
            int y = Integer.parseInt(scanner.next());
            setLocation(x, y);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveCoordinates() {
        Rectangle b = getBounds();
        String coordinates = "" + b.x + ',' + b.y;
        try (FileWriter writer = new FileWriter("Coordinates")) {
            writer.write(coordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserInput() {
        return jTextField.getText();
    }

    @Override
    public void clearUserInput() {
        jTextField.setText("");
    }

    @Override
    public void displayResultToUser(String text) {
        jTextField.setText(text);
    }

    @Override
    public void displayNotificationToUser(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    @Override
    public void deleteOneSymbol() {
        String val = jTextField.getText();
        if (val != null && val.length() > 1) {
            jTextField.setText(val.substring(0, val.length() - 1));
        }
    }
}
