package TestNG.TestNgBase;

import FileWrappers.ReadFromPropertiesFile;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class TestGridBase {

    protected WebDriver driver=null;

    @BeforeClass
    @Parameters(value = {"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "D:\\testing\\Tools\\chrome.exe");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);
        cap.setVersion("114.0.5735.199");		//To specify the browser version

        URL url = new URL("http://localhost:4444/wd/hub");
        try {
            RemoteWebDriver driver = new RemoteWebDriver(url,  cap);

        }catch (Exception x)
        {
            System.out.println(x.getMessage());
        }
    }

    @AfterClass
    public void stopDriver()
    {
        driver.quit();
    }

}

