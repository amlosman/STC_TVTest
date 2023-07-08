import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features={"src/test/resources/Features"}
        ,glue= {"StepDefinition", "CucumberBase","Actions","Pages","FileWrappers","webBrowser"},
        publish=true,
        plugin= {"pretty", "html:test-output"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
