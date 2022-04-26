package com.carousell.commands.impl;

import java.util.List;

import com.carousell.commands.Command;

public class GetTopCategoryCommand implements Command{

    @Override
    public String runCmd(String input, String... additionalParams) {
        List<String> cmdArgs = parseCmd(input);
        return dataStore.getTopCategory(cmdArgs.get(1));
    }
    
}
