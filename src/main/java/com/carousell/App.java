package com.carousell;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.carousell.commands.Command;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    private Properties config;

    public App() {
        if (config == null) {
            loadConfig();
        }
    }

    private void loadConfig() {
        try (InputStream configFileStream = App.class.getClassLoader().getResourceAsStream("config.properties")) {
            this.config = new Properties();
            if (configFileStream == null) {
                LOGGER.info("Unable to find config.properties");
                return;
            }

            config.load(configFileStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to load config.properties ", e);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        Console console = System.console();
        boolean exit = false;
        do {
            String inputCmdLine = console.readLine("# ");
            if (!"EXIT".equalsIgnoreCase(inputCmdLine)) {
                try {
                    String className = app.config.getProperty(inputCmdLine.split(" ")[0].toUpperCase());
                    if (className != null) {
                        Class clazz = Class.forName(className);
                        Command commandClazz = (Command) clazz.newInstance();
                        System.out.println(commandClazz.runCmd(inputCmdLine, new String[0])); 
                        System.out.println();
                    } else {
                        System.out.println("Invalid Command. Please try again with correct syntax!");
                    }

                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Unable to load the mapping ", e);
                }
            } else {
                exit = true;
            }

        } while (!exit);
    }

}
