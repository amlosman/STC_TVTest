package TestNG.TestCases;

import FileWrappers.ReadDataFromJSonFile;
import Pages.SubscriptionPage;
import TestNG.TestNgBase.TestNGBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestsUsingTestNG extends TestNGBase {
    SoftAssert softAssert = new SoftAssert();
    SubscriptionPage subscriptionPage;
    @Severity(SeverityLevel.TRIVIAL)
    @Description("validate Choosing Plan according selection country")
    @Test(dataProvider = "Data")
    public void validateChoosingPlanTest(String Country, String LitePrice, String Currency, String ClassicPrice, String PremiumPrice, String icon) {
        subscriptionPage = new SubscriptionPage(getDriver());
        Assert.assertTrue(subscriptionPage.STCTVPageIsDisplayed());
        subscriptionPage.clickOnCountrySelection();
        Assert.assertTrue(subscriptionPage.countryListDisplayed());
        subscriptionPage.selectCountry(Country);
        Assert.assertEquals(subscriptionPage.getLiteValue(), LitePrice);
        Assert.assertTrue(subscriptionPage.getLiteCurrency().contains(Currency));
        Assert.assertEquals(subscriptionPage.getClassicValue(), ClassicPrice);
        Assert.assertEquals(subscriptionPage.getClassicCurrency(), subscriptionPage.getLiteCurrency());
        Assert.assertEquals(subscriptionPage.getPremiumValue(), PremiumPrice);
        Assert.assertEquals(subscriptionPage.getPremiumCurrency(), subscriptionPage.getLiteCurrency());
        Assert.assertEquals(subscriptionPage.getCountryName(), icon);
        softAssert.assertAll();

    }


    @DataProvider(name = "Data")
    public Object[][] dpMethod() {
        String[][] data = new String[3][6];
        ReadDataFromJSonFile json = new ReadDataFromJSonFile("src/test/resources/Data/VerificationData.json");
            data[0] = json.getValuesOf("KuwaitData", null).toArray(new String[0]);
            data[1] = json.getValuesOf("KSAData", null).toArray(new String[0]);
            data[2] = json.getValuesOf("BahrainData", null).toArray(new String[0]);

        return data;
    }


}
