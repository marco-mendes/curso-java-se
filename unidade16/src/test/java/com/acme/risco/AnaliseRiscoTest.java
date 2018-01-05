package com.acme.risco;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json" },
        features = "classpath:com/acme/risco/AnaliseRisco.feature"
)

public class AnaliseRiscoTest {
}
