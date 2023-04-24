package com.jwt.jwt.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationInput {
    public boolean validation(String input) {
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasLetter = letter.matcher(input);
        Matcher hasDigit = digit.matcher(input);
        return hasLetter.find() && hasDigit.find();
    }
}
