package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class cookiesDemo {
	
	//@Test(priority=1)
	void testCookies() {
		given()
		.when().get("https://www.google.com/")
		.then().cookie("AEC","ARSKqsJgm2-hw4qK55gBhx514oCdeZVVwBxIYfnEnMSTcNjPzqJoMGnhO20")
		.log().all();
	}
	
	@Test(priority=2)
	
	void getCookiesInfo() {
		Response res=given()
				.when().get("https://www.google.com/");
		
		//String cookie_value=res.getCookie("AEC");
		//System.out.println("Value of cookie is" +cookie_value);
		
		Map<String,String> cookies_values=res.getCookies();	
		//System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet()) {
			String cookie_value=res.getCookie(k);
			System.out.println(k+"  "+cookie_value);
		}
	}

}
