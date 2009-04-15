package com.wakaleo.training.babble.webtests;

import org.junit.Test;

public class PostMessageWebTest extends BaseStory {

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
}