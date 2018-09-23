package com.sbt.controller;

import com.sbt.model.Calculation;
import com.sbt.model.NotCorrectInputException;
import com.sbt.view.CalculatorView;

public class CalculateAction extends Action {

    public CalculateAction(CalculatorView view, Calculation calculation) {
        super(view, calculation);
    }

    @Override
    public void processAction() {
        String text = getView().getUserInput();
        if (!text.isEmpty())
            try {
                getView().displayResultToUser(getCalculation().calculate(text));
            } catch (NotCorrectInputException e) {
                getView().displayNotificationToUser(e.getMessage());
            }
    }
}
