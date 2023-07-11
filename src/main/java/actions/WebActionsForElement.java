package actions;

import com.google.common.io.Files;
import fileWrappers.LoggingHandling;
import fileWrappers.ReadFromPropertiesFile;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WebActionsForElement {
    WebDriver driver;
    public WebActionsForElement(WebDriver driver) {
        this.driver = driver;
    }
    /*wait to validate element*/
    public WebElement waitUntilCanValidate(By locator, ExpectedConditionsEnum condition) {
        try {
            WebElement element;
            switch (condition) {
                case presenceOfElement:
                    element = waitUntil((ExpectedConditions.presenceOfElementLocated(locator)));
                    return element;

                case ElementToBeClickable:
                    element = waitUntil((ExpectedConditions.elementToBeClickable(locator)));
                    return element;
                case visibilityOfElement:
                    element = waitUntil((ExpectedConditions.visibilityOfElementLocated(locator)));
                    return element;
                default:
                    System.out.println("Wrong Locator");
            }
            return null;
        } catch (Exception e) {
            LoggingHandling.logError(e);
            return null;

        }
    }
    /* Allocate Element*/
    public By returnElementLocatorBy(String selector, Locators l) {
        switch (l) {
            case XPath:
                return new By.ByXPath(selector);
            case id:
                return new By.ById(selector);
            case CSS:
                return new By.ByCssSelector(selector);
            case linkText:
                return new By.ByLinkText(selector);
            case Name:
                return new By.ByName(selector);
            default:
                return null;
        }
    }
    /*Click on element */
    public void clickOnElement(String selector, Locators l, ExpectedConditionsEnum ConditionOnCurrentPage) {

        By locator = returnElementLocatorBy(selector, l);
        waitUntilCanValidate(locator, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = waitUntilCanValidate(locator, ConditionOnCurrentPage);
        element.click();
    }
    /*get Text*/
    public String getElementText(String selector, Locators l, ExpectedConditionsEnum ConditionOnCurrentPage) {

        By locator = returnElementLocatorBy(selector, l);
        waitUntilCanValidate(locator, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = waitUntilCanValidate(locator, ConditionOnCurrentPage);
        return element.getText();
    }
    public boolean checkElementIsDisplayed(String selector, Locators l, ExpectedConditionsEnum ConditionOnCurrentPage) {
        By locator = returnElementLocatorBy(selector, l);
        waitUntilCanValidate(locator, ExpectedConditionsEnum.presenceOfElement);
        WebElement element = waitUntilCanValidate(locator, ConditionOnCurrentPage);
        return element != null;
    }
    /*implicit wait */
    public WebElement waitUntil(ExpectedCondition<WebElement> s) {
        try {
            return new WebDriverWait(driver, 1000).until(s);
        } catch (Exception e) {
            LoggingHandling.logError(e);
            return null;
        }
    }
    /*Locator type*/
    public enum Locators {
        XPath, CSS, id, linkText, Name

    }
    /*Expected Condition type*/
    public enum ExpectedConditionsEnum {
        presenceOfElement, ElementToBeClickable, visibilityOfElement
    }
    public  void captureScreenShot(WebDriver driver, String screenShotName) {
        //make a destination for the screenshot
        var camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        String Error =  System.getProperty("user.dir")+ String.format(ReadFromPropertiesFile.getValue("screenShot"),screenShotName);
        try {
            Files.move(screenshot, new File(Error));
        } catch (IOException e) {

            System.out.println("Exception while taking screenshot"+e.getMessage());
        }
    }
}
