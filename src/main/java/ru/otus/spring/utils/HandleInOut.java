package ru.otus.spring.utils;

import java.util.Scanner;

public class HandleInOut {

    private Scanner scanner;

    public HandleInOut() {
        resetSystemIn();
    }

    public void resetSystemIn() {
        this.scanner = new Scanner(System.in);
    }

    public String nextLineIn() {
        return this.scanner.nextLine().trim();
    }

    public void print(String s){
        System.out.print(s);
    }

    public void printLn(String s){
        System.out.println(s);
    }
}
