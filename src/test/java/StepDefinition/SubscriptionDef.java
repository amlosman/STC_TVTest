package StepDefinition;

import CucumberBase.Hooks;
import FileWrappers.ReadFromPropertiesFile;
import Pages.SubscriptionPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

public class SubscriptionDef {
    SoftAssert softAssert;
    SubscriptionPage subscriptionPage;
    public SubscriptionDef() {
        subscriptionPage = new SubscriptionPage(Hooks.getDriver());
        softAssert = new SoftAssert();
    }

    @Given("I navigated to STC TV site")
    public void iNavigatedToSTCTVSite() {
        Hooks.getDriver().navigate().to(ReadFromPropertiesFile.getValue("STC_TV_URL"));
        softAssert.assertAll();
    }

    @Then("I should see STC TV page displayed")
    public void iShouldSeeSTCTVPageDisplayed() {
        Assert.assertTrue(subscriptionPage.STCTVPageIsDisplayed());
        softAssert.assertAll();
    }

    @When("I select Country {string}")
    public void iSelectCountry(String Country) {
        subscriptionPage.selectCountry(Country);
    }


    @And("I should see Lite Price{string} and {string}")
    public void iShouldSeeLitePriceAnd(String price, String currency) {
        Assert.assertEquals(subscriptionPage.getLiteValue(), price);
        Assert.assertTrue(subscriptionPage.getLiteCurrency().contains(currency));
        softAssert.assertAll();
    }

    @And("I should see Classic Price{string}")
    public void iShouldSeeClassicPrice(String price) {
        Assert.assertEquals(subscriptionPage.getClassicValue(), price);
        Assert.assertEquals(subscriptionPage.getClassicCurrency(), subscriptionPage.getLiteCurrency());
        softAssert.assertAll();
    }

    @And("I should see Premium Price {string}")
    public void iShouldSeePremiumPrice(String price) {
        Assert.assertEquals(subscriptionPage.getPremiumValue(), price);
        Assert.assertEquals(subscriptionPage.getPremiumCurrency(), subscriptionPage.getLiteCurrency());
        softAssert.assertAll();
    }


    @Then("I should see country icon {string} is displayed")
    public void iShouldSeeCountryIconIsDisplayed(String icon) {
        Assert.assertEquals(subscriptionPage.getCountryName(), icon);
        softAssert.assertAll();
    }

    @When("I click on country selection")
    public void iClickOnCountrySelection() {
        subscriptionPage.clickOnCountrySelection();
    }

    @Then("I should see country list")
    public void iShouldSeeCountryList() {
        Assert.assertTrue(subscriptionPage.countryListDisplayed());
        softAssert.assertAll();
    }
}
