package com.joprog.icycle.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegexHelper {

    public boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}