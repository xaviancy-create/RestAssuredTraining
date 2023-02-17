package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUser {
	
	@Test
	void test_getuser(ITestContext context) {
		
		String bearerToken="841255fa0f8077488b683d442e94cd3efad52ce600b550f29a3d00a2aaca8992";
		int id= (Integer) context.getSuite().getAttribute("user_id");
		given().header("Authorization","Bearer "+bearerToken)
		.pathParam("id", id)
		.when().get("https://gorest.co.in/public/v2/users/{id}")
		.then().statusCode(200).log().all();
		
	}

}
