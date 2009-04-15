package com.wakaleo.training.babble.webtests;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.thoughtworks.selenium.SeleneseTestCase;

public class BaseStory extends SeleneseTestCase {

   /**
     * Commons Logging.
     */
    private static final Log log = LogFactory.getLog(BaseStory.class);
 
    
	public void setUp() throws Exception {
    	
    	URL url =  ClassLoader.getSystemResource("config.properties");
    	Properties config = new Properties();
    	config.load(new FileInputStream(new File(url.getFile())));

    	String jettyPort=config.getProperty("jetty.port");
    	String targetBrowser=config.getProperty("target.browser");
		log.debug("Using jetty on port " + jettyPort);
		log.debug("Target browser: " + targetBrowser);
		setUp("http://localhost:" + jettyPort, targetBrowser);
	}
    
    public void userLogsOn(String username) throws Exception {
        selenium.open("/babble-webtest/");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Login!");
        selenium.type("username", username);
        selenium.click("action");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Hi " + username + "! Say something meaningless");
	}    
    
}