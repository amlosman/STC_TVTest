package Pages;

import Actions.WebActionsForElement;
import FileWrappers.ReadDataFromJSonFile;
import org.openqa.selenium.WebDriver;

public class SubscriptionPage {


    WebDriver driver;
    ReadDataFromJSonFile readDataFromJSonFile = new ReadDataFromJSonFile(ReadDataFromJSonFile.jsonPath);
    public SubscriptionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCountrySelection() {
        new WebActionsForElement(driver).clickOnElement(readDataFromJSonFile.getValueOfNode("countrySelectionID"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);

    }

    public void selectCountry(String country) {
        switch (country) {
            case "Kuwait": {
                new WebActionsForElement(driver).clickOnElement(readDataFromJSonFile.getValueOfNode("kuwaitID"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);
                break;
            }
            case "KSA": {
                new WebActionsForElement(driver).clickOnElement(readDataFromJSonFile.getValueOfNode("KSAID"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);
                break;
            }
            case "Bahrain": {
                new WebActionsForElement(driver).clickOnElement(readDataFromJSonFile.getValueOfNode("bahrainID"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.ElementToBeClickable);
                break;
            }
            default:
                System.out.println("Wrong choose");
        }
    }

    public String getLiteValue()
    {
        String lite = new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("currencyLite"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
        String[] values = lite.split(" ");
        return values[0];
    }
    public boolean STCTVPageIsDisplayed()
    {
        return  new WebActionsForElement(driver).checkElementIsDisplayed(readDataFromJSonFile.getValueOfNode("header"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
    }
    public boolean countryListDisplayed()
    {
        return  new WebActionsForElement(driver).checkElementIsDisplayed(readDataFromJSonFile.getValueOfNode("countryList"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
    }
    public String getLiteCurrency()
    {
        String lite = new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("currencyLite"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
        String[] values = lite.split(" ");
        return values[1];
    }
    public String getClassicValue()
    {
        String lite = new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("currencyClassic"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
        String[] values = lite.split(" ");
        return values[0];
    }
    public String getClassicCurrency()
    {
        String lite = new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("currencyClassic"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
        String[] values = lite.split(" ");
        return values[1];
    }
    public String getPremiumValue()
    {
        String lite = new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("currencyPremium"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
        String[] values = lite.split(" ");
        return values[0];
    }
    public String getPremiumCurrency()
    {
        String lite = new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("currencyPremium"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
        String[] values = lite.split(" ");
        return values[1];
    }
    public String getCountryName()
    {
        return new WebActionsForElement(driver).getElementText(readDataFromJSonFile.getValueOfNode("countryName"), WebActionsForElement.Locators.id, WebActionsForElement.ExpectedConditionsEnum.presenceOfElement);
    }
}