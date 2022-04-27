package com.carousell.utils;

public class StringUtils {
    
    private StringUtils() {
    }

    public static String unQuote(String input){
        return (input!=null && input.startsWith("'")) ? input.substring(1,input.length()-1) : input;
    }
}
