package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req=null;

	public RequestSpecification requestSpecification() throws IOException {

		
		//This 'if' prevent log file overriding with new instance. but I dont understand that
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

		}
		
		return req;

	}

	public static String getGlobalValues(String baseURI) throws IOException {
		Properties prop = new Properties();
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties";
		// C:\Users\dhanushkach\eclipse-workspace\CucumberREST\src\test\java\resources\global.properties
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		String propV = prop.getProperty(baseURI);
		System.out.println(propV);
		System.out.println(path);

		return propV;

	}

}
