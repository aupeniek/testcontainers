package org.selenide.examples;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.*;
import java.util.Properties;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class InitTestWithDocker {

  @Rule
  public BrowserWebDriverContainer chrome =
      new BrowserWebDriverContainer()
          .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("build"))
          .withCapabilities(DesiredCapabilities.chrome());

  @Before
  public void setUp() {
    RemoteWebDriver driver = chrome.getWebDriver();
    WebDriverRunner.setWebDriver(driver);
  }

  @After
  public void tearDown() {
    WebDriverRunner.closeWebDriver();
  }

  public Properties getProperties() {
    Properties prop = new Properties();
    String fileName = "config.properties";
    InputStream is = null;
    try {
      is = new FileInputStream(fileName);
    } catch (FileNotFoundException ex) {

    }
    try {
      prop.load(is);
    } catch (IOException ex) {

    }
    return prop;
    //System.out.println("Config entry: " + prop.getProperty("portal.URL"));
    //System.out.println("Config entry: " + prop.getProperty("portal.email"));
    //System.out.println("Config entry: " + prop.getProperty("portal.password"));
  }
}
