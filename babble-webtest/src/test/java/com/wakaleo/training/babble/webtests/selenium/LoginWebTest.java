package com.wakaleo.training.babble.webtests.selenium;

import org.junit.Test;

public class LoginWebTest extends BaseStory {

    @Test
	public void testHomePageShouldDisplayTitle() throws Exception {
        browser.open("/babble-webtest/");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Babble - meaningless utterances!");
	}

    @Test
	public void testUserLogsInByEnteringUserName() throws Exception {
        browser.open("/babble-webtest/");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Login!");
        browser.type("username", "jack");
        browser.click("action");
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Hi jack! Say something meaningless");
	}

    @Test
	public void testUserCanLogOff() throws Exception {
        userLogsOn("Jack");
        browser.click("//input[@name='action' and @value='logout']");               
        browser.waitForPageToLoad("10000");
        browser.isTextPresent("Login!");
	}


    
}