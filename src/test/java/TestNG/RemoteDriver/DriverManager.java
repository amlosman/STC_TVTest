package TestNG.RemoteDriver;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverManager {

    /*
    This simple line does all the mutlithread magic.
    For more details please refer to the link above :)
    */
    private static final ThreadLocal<WebDriver> remoteWebDriver = new ThreadLocal<WebDriver>();


    public static WebDriver getDriver() {

        return remoteWebDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        remoteWebDriver.set(driver);
    }

    /**
     * Returns a string containing current browser name, its version and OS name.
     * This method is used in the the *WebDriverListeners to change the test name.
     */
    public static String getBrowserInfo() {

        // we have to cast WebDriver object to RemoteWebDriver here, because the first one does not have a method
        // that would tell you which browser it is driving. (sick!)
        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String b = cap.getBrowserName();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion();
        return String.format("%s v:%s %s", b, v, os);
    }
}