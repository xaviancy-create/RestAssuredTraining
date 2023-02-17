package day4;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	//@Test
	void parsingJSONResponse() {
		given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/store")
		.then().statusCode(200).header("Content-Type", "application/json; charset=utf-8")
		.body("book[3].title", equalTo("The Lord"));
	}
	//@Test
	void parsingJSONResponse1() {
		Response res=given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/store");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String bookName=res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord");
	}
	//@Test
	void parsingJSONResponse2() {
		Response res=given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/store");
		JSONObject jo=new JSONObject(res.asString());
		for(int i=0; i<jo.getJSONArray("book").length();i++) {
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
	}
	//@Test
	void parsingJSONResponse3() {
		Response res=given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/store");
		JSONObject jo=new JSONObject(res.asString());
		boolean status=false;
		for(int i=0; i<jo.getJSONArray("book").length();i++) {
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("The Lord")) {
				status=true;
				break;
			}
			
		}
		Assert.assertEquals(status, true);
	}
	@Test
	void parsingJSONResponse4() {
		Response res=given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/store");
		JSONObject jo=new JSONObject(res.asString());
		boolean status=false;
		for(int i=0; i<jo.getJSONArray("book").length();i++) {
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("The Lord")) {
				status=true;
				break;
			}
			
		}
		Assert.assertEquals(status, true);
		
		double totalprice=0;
		for(int i=0; i<jo.getJSONArray("book").length();i++) {
			String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalprice=totalprice+Double.parseDouble(price);
		
	}
		System.out.println("Total price of book is:"+totalprice);
		Assert.assertEquals(totalprice, 552.0);
	}

}
