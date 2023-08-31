package com.thinesh.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = {"com.thinesh.steps", "com.thinesh.hooks"},
        monochrome = true,
        plugin = {"rerun:failed_scenarios/failed_scenarios.txt", "pretty",
                "html:target/cucumber-reports/reports.html",
                "json:target/cucumber-reports/reports.json",
                "junit:target/cucumber-reports/reports.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        
        
)
public class TestRunner {
}
