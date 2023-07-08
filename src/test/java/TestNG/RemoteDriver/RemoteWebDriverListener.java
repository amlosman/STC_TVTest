package TestNG.RemoteDriver;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;


import org.testng.internal.BaseTestMethod;
import org.testng.log4testng.Logger;

public class RemoteWebDriverListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            URL hubURL = null;
            try {
                // get hub URL specified in the TestNG XML test suite file
                hubURL = new URL(method.getTestMethod().getXmlTest().getParameter("hubURL"));
            } catch (MalformedURLException e) {
                System.out.println("ex:\n" + e.getMessage() + "");
                e.printStackTrace();
            }
            // get and set new instance of remote WebDriver
            WebDriver driver = RemoteDriverFactory.createInstance(hubURL, browserName);
            DriverManager.setWebDriver(driver);
        } else {
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String browser = DriverManager.getBrowserInfo();
            try {
                BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
                Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
                f.setAccessible(true);
                String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - " + bm.getMethodName() + " - " + browser;
                f.set(bm, newTestName);
            } catch (Exception ex) {
                System.out.println("ex" + ex.getMessage());
            } finally {
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }
}