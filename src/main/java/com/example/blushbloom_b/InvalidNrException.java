package com.example.blushbloom_b;
public class InvalidNrException extends Exception {
    public InvalidNrException(String selectedOption) {
        super("Invalid category selection: " + selectedOption + ". Please choose a number between 1 and 6.");
    }
}
