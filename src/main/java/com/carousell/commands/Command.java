package com.carousell.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.carousell.common.DataStore;
import com.carousell.utils.StringUtils;

public interface Command {

    DataStore dataStore = new DataStore();

    default List<String> parseCmd(String input){
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'").matcher(input); //([^\"]\\S*|\".+?\")\\s*
        while (m.find())
            list.add(StringUtils.unQuote(m.group()));

        return list;    
    }
    String runCmd(String input,String... additionalParams);
}
