package com.carousell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final Logger LOGGER = Logger.getLogger(AppTest.class.getName());
    
    private Properties config;

    public AppTest() {
        if (config == null) {
            loadConfig();
        }
    }

    private void loadConfig() {
        try (InputStream configFileStream = AppTest.class.getClassLoader().getResourceAsStream("test-case-config.properties")) {
            this.config = new Properties();
            config.load(configFileStream);
            
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to load test-case-config.properties ", e);
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void runAllTestCases()
    {
        App app = new App();
        for(int i=1;i<=Integer.parseInt(this.config.getProperty("testcases.size"));i++){
            String inputCmdLine = this.config.getProperty("testcase."+i+".input");
            System.out.println("# "+inputCmdLine);
            try {
                String response = app.runCommand(inputCmdLine);
                System.out.println("Command Response \t:\n"+response);
                String outputExpected = this.config.getProperty("testcase."+i+".output");
                if(outputExpected==null){
                    outputExpected = this.config.getProperty("testcase."+i+".output.startsWith");
                    response = response.substring(0,outputExpected.length());
                }

                System.out.println("Test Case Expected \t : "+outputExpected);
                System.out.println("Test Case Actual \t : "+response);
                assertEquals(outputExpected, response);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("===========================================================================");
        }
        assertTrue( true );
    }
}
