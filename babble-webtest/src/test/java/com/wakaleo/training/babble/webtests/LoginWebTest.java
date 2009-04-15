package com.wakaleo.training.babble.webtests;

import org.junit.Test;

public class LoginWebTest extends BaseStory {

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
	public void testUserCanLogOff() throws Exception {
        userLogsOn("Jack");
        selenium.click("//input[@name='action' and @value='logout']");               
        selenium.waitForPageToLoad("10000");
        selenium.isTextPresent("Login!");
	}


    
}