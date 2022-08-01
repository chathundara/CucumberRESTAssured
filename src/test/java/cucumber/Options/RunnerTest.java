package cucumber.Options;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = {
		"stepDefinition" }, plugin = "json:target/JsonReports/cucumber-report.json")
public class RunnerTest {
	// Below adding cucmber report data section from https://github.com/damianszczepanik/cucumber-reporting
	@AfterClass
	public static void tearDown() {
		File reportOutputDirectory = new File("target/maven-cucumber-report");
		List<String> jsonFiles = new ArrayList<>();

		jsonFiles.add("target/JsonReports/cucumber-report.json");

		String buildNumber = "1";
		String projectName = "Dann API Cucumber Project";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);

		configuration.setBuildNumber(buildNumber);
		// addidtional metadata presented on main page
		configuration.addClassifications("Platform", System.getProperty("os.name").toUpperCase());
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Branch", "release/1.0");
		configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
		//This is added by Indian guy
		// and here validate 'result' to decide what to do if report has failed
		//This comment from american guy
		//going to commit
	}
}
