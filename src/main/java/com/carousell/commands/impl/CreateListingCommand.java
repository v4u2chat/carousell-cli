package com.carousell.commands.impl;

import java.util.Date;
import java.util.List;

import com.carousell.commands.Command;
import com.carousell.common.Listing;

public class CreateListingCommand implements Command{

    @Override
    public String runCmd(String input, String... additionalParams) {
        
        List<String> cmdArgs = parseCmd(input);
        Listing listing = new Listing();
        listing.setUserName(cmdArgs.get(1));
        listing.setTitle(cmdArgs.get(2));
        listing.setDesc(cmdArgs.get(3));
        listing.setPrice(Double.parseDouble(cmdArgs.get(4)));
        listing.setCreatedTime(new Date());
        listing.setCategory(cmdArgs.get(5));

        return dataStore.addListing(listing, cmdArgs.get(1), cmdArgs.get(5));
    }

    
    
}
