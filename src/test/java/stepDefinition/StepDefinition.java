package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild builder;
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		builder = new TestDataBuild();
		res = given().spec(requestSpecification()).body(builder.addPlacePayload(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIresources resourceAPI = APIresources.valueOf(resource);

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResources()).then().spec(resspec).extract().response();
		else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResources());
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = res.when().delete(resourceAPI.getResources()).then().spec(resspec).extract().response();
		}
	}

	@Then("the API calls gotm success with status code {int}")
	public void the_api_calls_gotm_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
		// System.out.println(response.getBody().path(string).toString());

		assertEquals(response.getBody().path(string).toString(), string2);
	}

	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		// System.out.println(response.path("place_id").toString());
		place_id = response.path("place_id").toString();
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		// call previously defined common method
		user_calls_with_http_request(resource, "GET");
		System.out.println(response.asPrettyString());
		String actualName = response.path("name").toString();
		
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		
		builder = new TestDataBuild();
		System.out.println(place_id);
		res = given().spec(requestSpecification()).body(builder.deletePlacePayload(place_id));

	}

}
