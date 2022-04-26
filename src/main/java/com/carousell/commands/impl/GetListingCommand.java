package com.carousell.commands.impl;

import java.util.List;

import com.carousell.commands.Command;

public class GetListingCommand implements Command{

    @Override
    public String runCmd(String input, String... additionalParams) {
        List<String> cmdArgs = parseCmd(input);
        return dataStore.getListing(cmdArgs.get(1), Integer.parseInt(cmdArgs.get(2)));
    }
    
}
