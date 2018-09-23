package com.sbt.model;

import java.util.LinkedList;

class MathBlock {

    static double getResult(LinkedList<String> post) throws NotCorrectInputException {
        LinkedList<Double> numbers = new LinkedList<>();
        while (!post.isEmpty()) {
            if (post.getFirst().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                numbers.add(Double.parseDouble(post.getFirst()));
            } else switch (post.getFirst()) {
                case "+": {
                    addition(numbers);
                    break;
                }
                case "-": {
                    subtraction(numbers);
                    break;
                }
                case "*": {
                    multiplication(numbers);
                    break;
                }
                case "/": {
                    division(numbers);
                    break;
                }
            }
            post.removeFirst();

        }
        return numbers.getFirst();
    }

    private static void addition(LinkedList<Double> numbers) throws NotCorrectInputException {
        double a = numbers.getLast();
        numbers.removeLast();
        if (numbers.isEmpty()) throw new NotCorrectInputException("Неправильный ввод");
        a += numbers.getLast();
        numbers.removeLast();
        numbers.add(a);
    }

    private static void subtraction(LinkedList<Double> numbers) {
        double a = numbers.getLast();
        numbers.removeLast();
        if (!numbers.isEmpty()) {
            a = numbers.getLast() - a;
            numbers.removeLast();
        } else a = -a;
        numbers.add(a);
    }

    private static void multiplication(LinkedList<Double> numbers) throws NotCorrectInputException {
        double a = numbers.getLast();
        numbers.removeLast();
        if (numbers.isEmpty()) throw new NotCorrectInputException("Неправильный ввод");
        a = numbers.getLast() * a;
        numbers.removeLast();
        numbers.add(a);
    }

    private static void division(LinkedList<Double> numbers) throws NotCorrectInputException {
        double a = numbers.getLast();
        numbers.removeLast();
        if (numbers.isEmpty()) throw new NotCorrectInputException("Неправильный ввод");
        double b = numbers.getLast();
        String c = String.format("%.12f", b);
        if (c.contains("."))
            while (c.charAt(0) != '.') c = c.substring(1);
        c=c.substring(1);
        for (int i = 0; i < c.length(); i++) {
            a = a * 10;
            b = b * 10;
        }
        a = b / a;
        numbers.removeLast();
        numbers.add(a);
    }
}
