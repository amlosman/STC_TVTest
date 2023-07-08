package TestNG.TestNgBase;

import FileWrappers.ReadFromPropertiesFile;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import webBrowser.WebBrowsers;

public class TestNGBase {
    WebDriver driver;

    @Parameters(value = {"browser"})
    @BeforeTest
    public void before(@Optional("Chrome") String browser) {
        driver = WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf(browser), ReadFromPropertiesFile.getValue("headless").equals("yes"));
        WebBrowsers.staticmaximizeWindow(driver);
        getDriver().navigate().to(ReadFromPropertiesFile.getValue("STC_TV_URL"));

    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void afterClass() {
        saveScreenInAllureReport(getDriver());
        WebBrowsers.quitWindow(driver);
    }

    @Attachment
    public byte[] saveScreenInAllureReport(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
