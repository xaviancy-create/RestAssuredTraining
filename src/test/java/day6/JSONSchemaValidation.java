package day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation {
	
	//@Test
	
	void jsonSchemaValidation() {
		given().when().get("http://localhost:3000/store")
		.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("StoreJSONSchema.json"));
	}
	
	@Test
	void xmlSchemaValidation() {
		given().when().get("http://localhost:3000/store")
		.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("StoreXMLSchema.xsd"));
	}
	
	
	

}
