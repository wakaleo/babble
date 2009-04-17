package com.wakaleo.training.babble.webtests.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;


import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class BaseStory {

   /**
     * Commons Logging.
     */

    protected Selenium browser;
    
    @Before
	public void setUp() throws Exception {
    	
      	URL url =  ClassLoader.getSystemResource("config.properties");
    	Properties config = new Properties();
    	config.load(new FileInputStream(new File(url.getFile())));

       	String jettyPort=config.getProperty("jetty.port");
       	String seleniumPort=config.getProperty("selenium.port");
       	String targetBrowser=config.getProperty("target.browser");
        browser = new DefaultSelenium("localhost",
                					  Integer.valueOf(seleniumPort), 
                					  targetBrowser, 
                					  "http://localhost:" + jettyPort);
        browser.start();
	}
    
	@After
    public void tearDown() {
        browser.stop();
    }
    
    public void userLogsOn(String username) throws Exception {
        browser.open("/babble-webtest/");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Login!");
        browser.type("username", username);
        browser.click("action");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Hi " + username + "! Say something meaningless");
	}    
    
    
}