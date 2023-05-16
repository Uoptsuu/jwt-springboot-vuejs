package com.jwt.jwt.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationInput {
    public boolean validatePassword(String input) {
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasLetter = letter.matcher(input);
        Matcher hasDigit = digit.matcher(input);
        Matcher hasSpecial = special.matcher(input);
        return hasLetter.find() && hasDigit.find() && hasSpecial.find();
    }

    public boolean validateUsername(String input) {
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(input);
        return hasSpecial.find();
    }
}
