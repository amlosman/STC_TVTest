package stepDefinition;

import actions.WebActionsForElement;
import com.google.common.io.Files;
import base.Base;
import fileWrappers.ReadFromPropertiesFile;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import webBrowser.WebBrowsers;

import java.io.File;
import java.io.IOException;

public class HomeStepdef {
    HomePage homePage;
    WebDriver driver;
    public HomeStepdef() {
        driver = WebBrowsers.chooseBrowserDriver(WebBrowsers.BrowserEnum.valueOf("Chrome"), ReadFromPropertiesFile.getValue("headless").equals("yes"));
        WebBrowsers.staticmaximizeWindow(driver);
        homePage = new HomePage(driver);
        
    }

    @Given("I navigated to STC TV site")
    public void iNavigatedToSTCTVSite() {
        driver.navigate().to(ReadFromPropertiesFile.getValue("STC_TV_URL"));
    }

    @Then("I should see STC TV page displayed")
    public void iShouldSeeSTCTVPageDisplayed() {
        Assert.assertTrue(homePage.STCTVPageIsDisplayed());
    }

    @When("I select Country {string}")
    public void iSelectCountry(String Country) {
        homePage.selectCountry(Country);
    }


    @And("I should see Lite Price{string} and {string}")
    public void iShouldSeeLitePriceAnd(String price, String currency) {
        Assert.assertEquals(homePage.getLiteValue(), price);
        Assert.assertTrue(homePage.getLiteCurrency().contains(currency));
    }

    @And("I should see Classic Price{string}")
    public void iShouldSeeClassicPrice(String price) {
        Assert.assertEquals(homePage.getClassicValue(), price);
        Assert.assertEquals(homePage.getClassicCurrency(), homePage.getLiteCurrency());
    }

    @And("I should see Premium Price {string}")
    public void iShouldSeePremiumPrice(String price) {
        Assert.assertEquals(homePage.getPremiumValue(), price);
        Assert.assertEquals(homePage.getPremiumCurrency(), homePage.getLiteCurrency());
    }


    @Then("I should see country icon {string} is displayed")
    public void iShouldSeeCountryIconIsDisplayed(String icon) {
        Assert.assertEquals(homePage.getCountryName(), icon);
    }

    @When("I click on country selection")
    public void iClickOnCountrySelection() {
        homePage.clickOnCountrySelection();
    }

    @Then("I should see country list")
    public void iShouldSeeCountryList() {
        Assert.assertTrue(homePage.countryListDisplayed());
    }
    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
           new WebActionsForElement(driver).captureScreenShot(driver,scenario.getId());
        }
        if(driver!=null)
        WebBrowsers.quitWindow(driver);
    }
}
