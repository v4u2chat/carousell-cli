package com.carousell.commands.impl;

import com.carousell.commands.Command;

public class RegisterCommand implements Command{

    @Override
    public String runCmd(String input, String... additionalParams) {
        return dataStore.registerUser(parseCmd(input).get(1)) ? "Success" : "Error - user already existing";
    }
    
}
