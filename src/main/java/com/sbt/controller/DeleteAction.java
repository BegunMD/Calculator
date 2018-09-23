package com.sbt.controller;


import com.sbt.model.Calculation;
import com.sbt.view.CalculatorView;

public class DeleteAction extends Action {

    public DeleteAction(CalculatorView view, Calculation calculation) {
        super(view, calculation);
    }

    @Override
    public void processAction() {
        getView().deleteOneSymbol();
    }
}



