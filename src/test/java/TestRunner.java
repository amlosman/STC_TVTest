import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(

        features={"src/test/resources/features"}
        ,glue= {"stepDefinition","actions", "pages", "fileWrappers","webBrowser"},
        publish=true,
        plugin= {"pretty", "html:test-output"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
