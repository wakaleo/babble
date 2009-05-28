package com.wakaleo.training.babble.webtests.selenium;

import org.junit.After;
import org.junit.Before;


import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class BaseStory {

   /**
     * Commons Logging.
     */

    protected Selenium browser;
    protected String serverPort;
    protected String seleniumPort;
    protected String targetBrowser;
    protected String context;
    
    public BaseStory() {
      	serverPort = System.getProperty("server.port");
       	seleniumPort = System.getProperty("port");
       	targetBrowser = System.getProperty("target.browser");
       	context = System.getProperty("target.context");

       	System.out.println("Selenium port = " + seleniumPort);
       	if (serverPort == null) {
       		serverPort="9090";
       	}
       	if (seleniumPort == null) {
       		seleniumPort="4444";
       	}
       	if (targetBrowser == null) {
       		targetBrowser="*firefox";
       	}
       	if (context == null) {
       		context="babble-web";
       	}
    }
    
    @Before
	public void setUp() throws Exception {
    	
       	browser = new DefaultSelenium("localhost",
                					  Integer.valueOf(seleniumPort), 
                					  targetBrowser, 
                					  "http://localhost:" + serverPort);
        browser.start();
	}
    
    public String getBaseUrl() {    	
    	return "http://localhost:" + serverPort + "/" + context;
    }
    
	@After
    public void tearDown() {
        browser.stop();
    }
    
    public void userLogsOn(String username) throws Exception {
        browser.open("/babble-web/");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Login!");
        browser.type("username", username);
        browser.click("action");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Hi " + username + "! Say something meaningless");
	}   
    
    public void userLogsOff() {
        browser.click("//input[@name='action' and @value='logout']");               
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Login!");
    }
    
}