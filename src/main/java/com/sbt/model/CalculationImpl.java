package com.sbt.model;

import java.text.DecimalFormat;

public class CalculationImpl implements Calculation {
    @Override
    public String calculate(String input) throws NotCorrectInputException {
        RPN rpn = new RPN(input).getResult(); //rpn if input correct
        return correctResult(MathBlock.getResult(rpn.getPost()));
    }

    private static String correctResult(double dresult) throws NotCorrectInputException {
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(8);
        df.setGroupingUsed(false);
        String result = df.format(dresult);
        result = result.replaceAll(",", ".");
        if (result.equals("-0")) result = "0";
        return result;
    }
}
