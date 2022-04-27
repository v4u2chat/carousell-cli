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
                    String response = app.runCommand(inputCmdLine);
                    console.printf(response);
                    console.printf("\n");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    LOGGER.log(Level.SEVERE, "Unable to load the mapping ", e);
                }
            } else {
                exit = true;
            }

        } while (!exit);
    }

    public String runCommand(String inputCmdLine) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = this.config.getProperty(inputCmdLine.split(" ")[0].toUpperCase());
        if (className != null) {
            Class<?> clazz = Class.forName(className); //<com.carousell.commands.Command>
            Command commandClazz = (Command) clazz.newInstance();
            return commandClazz.runCmd(inputCmdLine);
            
        } else {
            return "Invalid Command. Please try again with correct syntax!";
        }
    }

}
