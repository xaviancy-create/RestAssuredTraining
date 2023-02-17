package day7;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {
	
	//@Test
	void testBasicAuthentications() {
		
		given().auth().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200).body("authenticated", equalTo(true))
		.log().all();
		
	}
	
	//@Test
	void testDigestAuthentications() {
		
		given().auth().digest("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200).body("authenticated", equalTo(true))
		.log().all();
		
	}
	
	//@Test
	void testpreemptiveAuthentications() {
		
		given().auth().preemptive().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200).body("authenticated", equalTo(true))
		.log().all();
		
	}
	
	//@Test
	void testBearerTokenAuthentication() {
		String bearerToken="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";
		given().header("Authorization","Bearer "+bearerToken)
		.when().get("https://api.github.com/user/repos")
		.then().statusCode(200).log().all();
	}
	
	//@Test
	//void OAuth1Authentication() {
		//given().auth().oauth("consumerkey", "Consumer secret", "access token", "token secret") //This is get from developer
		//.when().get(null)
		//.then().statuscode(200).log().all();
	//}
	
	//@Test
	void testOAuth2Authentication() {
		given().auth().oauth2("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")
		.when().get("http://api.github.com/users")
		.then().statusCode(200).log().all();
	}
	
	//@Test
	void TestAPIKeyAuthentication() {
		given().queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
		.when().get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then().statusCode(200).log().all();
	}
	
		@Test
		void TestAPIKeyAuthenticationtype() {
			given().queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
			.when().get("https://api.openweathermap.org/{mypath}")
			.then().statusCode(200).log().all();
		}
		
	
	

}
