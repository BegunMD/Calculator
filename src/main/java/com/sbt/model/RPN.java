package com.sbt.model;

import com.sbt.model.MathBlock;
import com.sbt.model.NotCorrectInputException;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class RPN {

    private String input;
    private LinkedList<String> stack;

    public LinkedList<String> getPost() {
        return post;
    }

    private LinkedList<String> post;

    public RPN(String input) {
        this.input = input;
        stack = new LinkedList<>();
        post = new LinkedList<>();
    }



    /*public String getResult() throws NotCorrectInputException {
        if(!isCorrect()) throw new NotCorrectInputException("Неправильный ввод.");
        changeNotation();
        return thiscorrectResult();
    }*/

    public RPN getResult() throws NotCorrectInputException {
        if (!isCorrect()) throw new NotCorrectInputException("Неправильный ввод.");
        changeNotation();
        return this;
    }


    public boolean isCorrect() {
        return (input.matches("[-+.*/\\d\\(\\)]*") && !input.equals("()") && input.charAt(input.length() - 1) != '-');
    }

    public void changeNotation() throws NotCorrectInputException {
        while (!input.isEmpty()) {
            char c = input.charAt(0);
            if (Character.isDigit(c)) addNumber();
            else if (c == '(') open();
            else if (c == ')') close();
            else if (c=='.')  throw new NotCorrectInputException("Неправильный ввод.");
            else operator(c);
        }
        while (!stack.isEmpty()) {
            if (stack.getLast().equals("(")) throw new NotCorrectInputException("Не хватает закрывающей скобки");
            post.add(stack.getLast());
            stack.removeLast();
        }
    }



    private void addNumber() {
        StringBuilder number = new StringBuilder();
        while (!input.isEmpty() && (Character.isDigit(input.charAt(0)) || (input.charAt(0) == '.'))) {
            number.append(input.charAt(0));
            input = input.substring(1);
        }
        post.add(number.toString());
    }

    private void close() throws NotCorrectInputException {
        while (!stack.isEmpty() && !stack.getLast().equals("(")) {
            post.add(stack.getLast());
            stack.removeLast();
        }
        if (stack.isEmpty()) throw new NotCorrectInputException("Не хватает открывающей скобки"); //ошибка
        if (stack.getLast().equals("(")) stack.removeLast();
        input = input.substring(1);
    }

    private void open() {
        stack.add("(");
        input = input.substring(1);
        if (!input.isEmpty() && input.charAt(0) == '-' && Character.isDigit(input.charAt(1))) {
            input = input.substring(1);
            addNumber();
            String n = "-" + post.getLast();
            post.removeLast();
            post.add(n);
        }
    }

    private void operator(char c) {
        if (c == '*' || c == '/')
            while (!stack.isEmpty() && (stack.getLast().equals("/") || stack.getLast().equals("*"))) {
                post.add(stack.getLast());
                stack.removeLast();
            }
        else
            while (!stack.isEmpty() && !stack.getLast().equals("(")) {
                post.add(stack.getLast());
                stack.removeLast();
            }
        stack.add("" + c);
        input = input.substring(1);

    }


}

