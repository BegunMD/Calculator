package com.sbt.view;

public interface CalculatorView {
    String getUserInput();
    void clearUserInput();
    void displayResultToUser(String text);
    void displayNotificationToUser(String text);
    void deleteOneSymbol();
}
