package com.wakaleo.training.babble.webtests.selenium;

import org.junit.Test;

public class PostMessageWebTest extends BaseStory {

    @Test
	public void testUserLogsInAndEntersAMessage() throws Exception {
    	userLogsOn("jack");
        browser.click("//input[@name='action' and @value='babble']");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Hi jack! Say something meaningless");
        browser.type("message", "Hi there!");
        browser.isTextPresent("jack: Hi there!");
        browser.click("//input[@name='action' and @value='logout']");               
	}    

    @Test
	public void testMultipleUsersLogsInAndEnterMessages() throws Exception {
    	userLogsOn("jack");
        browser.click("//input[@name='action' and @value='babble']");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Hi jack! Say something meaningless");
        browser.type("message", "Hi there!");
        browser.isTextPresent("jack: Hi there!");
        browser.click("//input[@name='action' and @value='logout']");               
    	userLogsOn("jill");
        browser.click("//input[@name='action' and @value='babble']");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Hi jill! Say something meaningless");
        browser.type("message", "Hi Jack!");
        browser.isTextPresent("jack: Hi there!");
        browser.isTextPresent("jill: Hi Jack!");
        browser.click("//input[@name='action' and @value='logout']");               
	}    
}