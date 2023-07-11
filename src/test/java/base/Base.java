package base;

import fileWrappers.ReadFromPropertiesFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import webBrowser.WebBrowsers;

public class Base {
    static WebDriver driver;


    @BeforeTest(description = "navigate to the website and maximize the widow")
    public void setUp() {
        driver = WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf("Chrome"), ReadFromPropertiesFile.getValue("headless").equals("yes"));
        WebBrowsers.staticmaximizeWindow(driver);
        driver.navigate().to(ReadFromPropertiesFile.getValue("STC_TV_URL"));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterTest(description = "close all browser instance")
    public void tearDown() {
        if (driver != null) WebBrowsers.quitWindow(driver);
    }

}
