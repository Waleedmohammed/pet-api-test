package com.qa.pet.api.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@RunWith(Cucumber.class)
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, " + // Generate Human readable test results in logs
        "html:target/cucumber-report.html, " + // Generate HTML Report
        "json:target/cucumber-report.json, " + // Generate XML Report
        "junit:target/cucumber-reports.xml, " + // XML report to be used for CI tools (eg: Jenkins
        "timeline:target/cucumber-report,  " + // Identifiy parallel execution patterns
        "usage:target/usage.json, " + // Statistic about step definitions (number of time each step executed)
        "rerun:target/rerun.txt") // text file includes the failed scenarios (input to rerun only failed test scenarios
@ConfigurationParameter(key = "cucumber.publish.enabled", value = "true")
//@CucumberOptions(features = {"src/test/resources/features"})
public class RunCucumberTest {
}
