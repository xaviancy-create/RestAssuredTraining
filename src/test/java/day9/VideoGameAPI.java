package day9;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class VideoGameAPI {
	
	@Test(priority=1)
	void test_getAllVideoGames() {
		
		given().when().get("http://localhost:8080/app/videogames")
		.then().statusCode(200);
		
	}
	
	@Test(priority=2)
	void test_AddnewVideoGames() {
		
		HashMap data=new HashMap();      
		
		data.put("id", "100");
		data.put("name", "Post");
		data.put("releaseDate", "2023-02-17T04:29:24.510Z");
		data.put("reviewScore", "3");
		data.put("category", "Application");
		data.put("rating", "App");
		
		Response res= given().contentType("application/json").body(data)
		.when().post("http://localhost:8080/app/videogames")
		.then().statusCode(200).log().body().extract().response();
		
		String jsonString=res.asString();
		
		Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);
		
	}
	
	@Test(priority=3)
	void test_getVideoGame() {
		
		given().when().get("http://localhost:8080/app/videogames/100")
		.then().statusCode(200).log().body().body("videoGame.id", equalTo("100"))
		.body("videoGame.name", equalTo("Post"));
		
	}
	
	@Test(priority=4)
	void test_updateVideoGame() {
		HashMap data=new HashMap();      
		
		data.put("id", "100");
		data.put("name", "Pacman");
		data.put("releaseDate", "2023-02-17T04:29:24.510Z");
		data.put("reviewScore", "4");
		data.put("category", "Application");
		data.put("rating", "App");
		
		given().contentType("application/json").body(data)
		.when().put("http://localhost:8080/app/videogames/100")
		.then().statusCode(200).log().body().body("videoGame.id", equalTo("100"))
		.body("videoGame.name", equalTo("Pacman"));

}
	
	@Test(priority=5)
	
	void test_deleteVideoGame() {
		Response res=given().when().delete("http://localhost:8080/app/videogames/100")
		.then().statusCode(200).log().body().extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"),true);
	}
}
