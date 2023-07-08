package TestNG.TestCases;

import FileWrappers.ExcelFileManager;
import FileWrappers.ReadFromPropertiesFile;
import Pages.SubscriptionPage;
import TestNG.TestNgBase.TestNGBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import webBrowser.WebBrowsers;

import java.io.File;

public class Tests_UsingTestNG extends TestNGBase {
    SoftAssert softAssert = new SoftAssert();
    SubscriptionPage subscriptionPage;

    @Test(dataProvider = "Data")
    public void validateChoosingPlanTest(String Country, String LitePrice, String Currency, String ClassicPrice, String PremiumPrice, String icon) {
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
        ExcelFileManager excel = new ExcelFileManager(new File("src/test/resources/Data/VerifcationData.xlsx"));
        excel.switchToSheet("Sheet1");
        for (int i = 0; i < 3; i++) {
            data[i][0] = excel.getCellData("Country", i + 2);
            data[i][1] = excel.getCellData("LitePrice", i + 2);
            data[i][2] = excel.getCellData("Currency", i + 2);
            data[i][3] = excel.getCellData("ClassicPrice", i + 2);
            data[i][4] = excel.getCellData("PremiumPrice", i + 2);
            data[i][5] = excel.getCellData("icon", i + 2);
        }
        return data;
    }


}
