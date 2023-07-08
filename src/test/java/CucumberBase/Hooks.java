package CucumberBase;

import FileWrappers.ReadFromPropertiesFile;
import com.google.common.io.Files;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;
import webBrowser.WebBrowsers;

import java.io.File;
import java.io.IOException;

public class Hooks {
    static WebDriver driver;
    @Before
    public void before() {
        driver = WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf("Chrome"), ReadFromPropertiesFile.getValue("headless").equals("yes"));
        WebBrowsers.staticmaximizeWindow(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                String Error =  System.getProperty("user.dir")+ String.format(ReadFromPropertiesFile.getValue("screenShot"), scenario.getId());
                Files.move(screenshot, new File(Error));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        WebBrowsers.quitWindow(getDriver());
    }

}
