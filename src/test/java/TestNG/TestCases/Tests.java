package TestNG.TestCases;

import FileWrappers.ReadFromPropertiesFile;
import Pages.SubscriptionPage;
import TestNG.RemoteDriver.DriverManager;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class Tests {

    @Test
    public void validateChoosingPlanTest() {
        invokeBrowser(ReadFromPropertiesFile.getValue("STC_TV_URL"));
        SubscriptionPage subscriptionPage = new SubscriptionPage(DriverManager.getDriver());

        Assert.assertTrue(subscriptionPage.STCTVPageIsDisplayed());
        subscriptionPage.clickOnCountrySelection();
        Assert.assertTrue(subscriptionPage.countryListDisplayed());
        subscriptionPage.selectCountry("Kuwait");
        Assert.assertEquals(subscriptionPage.getLiteValue(), "1.2");
        Assert.assertTrue(subscriptionPage.getLiteCurrency().contains("KWD"));
        Assert.assertEquals(subscriptionPage.getClassicValue(), "2.5");
        Assert.assertEquals(subscriptionPage.getClassicCurrency(), subscriptionPage.getLiteCurrency());
        Assert.assertEquals(subscriptionPage.getPremiumValue(), "4.8");
        Assert.assertEquals(subscriptionPage.getPremiumCurrency(), subscriptionPage.getLiteCurrency());
        Assert.assertEquals(subscriptionPage.getCountryName(), "Kuwait");
    }

    private void invokeBrowser(String url) {
        DriverManager.getDriver().get(url);
    }
}
