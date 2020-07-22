package com.habeko.portal.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.selenide.examples.InitTestWithDocker;

import java.util.Properties;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;

public class LoginTest extends InitTestWithDocker {
    private static String portalURL;
    private static String portalUser;
    private static String portalPassword;

    @Before
    public void initProperties() {
        Properties properties = getProperties();
        this.portalURL = properties.getProperty("portal.URL");
        this.portalUser = properties.getProperty("portal.email");
        this.portalPassword = properties.getProperty("portal.password");
    }

    @Test
    public void loginTest() {
        System.out.println("portalURL: " + portalURL);
        open(portalURL);
        $(By.id("mat-input-0")).val(portalUser);
        $(By.id("mat-input-1")).val(portalPassword);
        $(By.id("mat-input-1")).sendKeys(Keys.ENTER);
        //$(byText("EINLOGGEN")).isDisplayed();
        SelenideElement userHeader = $(byClassName("userHeader"));
        userHeader.isDisplayed();
        System.out.println("-----> " + userHeader.text());

    }
}
