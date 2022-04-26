package com.carousell.commands.impl;

import java.util.List;

import com.carousell.commands.Command;

public class GetCategoryCommand implements Command{

    @Override
    public String runCmd(String input, String... additionalParams) {
        List<String> cmdArgs = parseCmd(input);
        return dataStore.getCategory(cmdArgs.get(2), cmdArgs.get(1), cmdArgs.get(3), cmdArgs.get(4));
    }
    
}
