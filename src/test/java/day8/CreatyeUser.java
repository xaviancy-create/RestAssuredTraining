package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class CreatyeUser {
	
	@Test
	void test_createuser(ITestContext context) {
		Faker faker=new Faker();
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Inactive");
		
		String bearerToken="841255fa0f8077488b683d442e94cd3efad52ce600b550f29a3d00a2aaca8992";
		int id=given().header("Authorization","Bearer "+bearerToken)
				.contentType("application/json")
				.body(data.toString())
				.when().post("https://gorest.co.in/public/v2/users")
				.jsonPath().getInt("id");
		System.out.println("Generated id is:"+id);
		context.getSuite().setAttribute("user_id",id);
		
		
	}

}
