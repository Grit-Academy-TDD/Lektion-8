package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static String printMessage(String message){
        return "The message is: " + message;
    }

    public enum Operator {
        addition,
        subtraction,
        division,
        multiplication
    }

    public static int calculate(int nr1, int nr2, Operator operator) {
        int result = nr1;
        switch (operator) {
            case addition -> result += nr2;
            case subtraction -> result -= nr2;
            case division -> result /= nr2;
            case multiplication -> result *= nr2;
        }
        return result;
    }
}