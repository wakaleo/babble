package com.wakaleo.training.babble.webtests;

import org.junit.Test;

import com.thoughtworks.selenium.SeleneseTestCase;
import org.junit.Before;

public class LoginStoryTest extends SeleneseTestCase {

    @Before
	public void setUp() throws Exception {
		setUp("http://localhost:8080/", "*firefox");
	}

    @Test
	public void testHomePageShouldDisplayTitle() throws Exception {
        selenium.open("/babble-webtest/");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Babble - meaningless utterances!");
	}

    @Test
	public void testUserLogsInByEnteringUserName() throws Exception {
        selenium.open("/babble-webtest/");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Login!");
        selenium.type("username", "jack");
        selenium.click("action");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Hi jack! Say something meaningless");
	}

    @Test
	public void testUserLogsInAndEntersAMessage() throws Exception {
    	userLogsOn("jack");
        selenium.click("//input[@name='action' and @value='babble']");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Hi jack! Say something meaningless");
        selenium.type("message", "Hi there!");
        selenium.isTextPresent("jack: Hi there!");
        selenium.click("//input[@name='action' and @value='logout']");               
	}    

    @Test
	public void testMultipleUsersLogsInAndEnterMessages() throws Exception {
    	userLogsOn("jack");
        selenium.click("//input[@name='action' and @value='babble']");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Hi jack! Say something meaningless");
        selenium.type("message", "Hi there!");
        selenium.isTextPresent("jack: Hi there!");
        selenium.click("//input[@name='action' and @value='logout']");               
    	userLogsOn("jill");
        selenium.click("//input[@name='action' and @value='babble']");
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Hi jill! Say something meaningless");
        selenium.type("message", "Hi Jack!");
        selenium.isTextPresent("jack: Hi there!");
        selenium.isTextPresent("jill: Hi Jack!");
        selenium.click("//input[@name='action' and @value='logout']");               
	}    

    
    @Test
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