package com.sbt.controller;

import com.sbt.model.Calculation;
import com.sbt.view.CalculatorView;

public abstract class Action {
    private CalculatorView view;
    private Calculation calculation;

    protected Action(CalculatorView view, Calculation calculation) {
        this.view = view;
        this.calculation = calculation;
    }

    public CalculatorView getView() {
        return view;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public void setView(CalculatorView view) {
        this.view = view;
    }

    public abstract void processAction();
}
