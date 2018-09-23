package com.sbt.controller;

import com.sbt.model.Calculation;
import com.sbt.view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ACAction extends Action {

    public ACAction(CalculatorView view, Calculation calculation) {
        super(view, calculation);
    }

    @Override
    public void processAction() {
        getView().clearUserInput();
    }
}