package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void test_deleteuser(ITestContext context) {
		String bearerToken="841255fa0f8077488b683d442e94cd3efad52ce600b550f29a3d00a2aaca8992";
		int id= (Integer) context.getSuite().getAttribute("user_id");
		given().header("Authorization","Bearer "+bearerToken)
		.pathParam("id", id)
		.when().delete("https://gorest.co.in/public/v2/users/{id}")
		.then().statusCode(204).log().all();
	}

}
