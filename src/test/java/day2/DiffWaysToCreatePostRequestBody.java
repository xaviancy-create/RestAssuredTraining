package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DiffWaysToCreatePostRequestBody {
	
	//@Test (priority=1)	
		void testPostUsingHashmap() {
			HashMap data=new HashMap();
			data.put("name", "Scott");
			data.put("location", "France");
			data.put("phone", "123456");
			String courseArr[]= {"c","c++"};
			data.put("course", courseArr);
			given().contentType("application/json").body(data)
			.when().post("http://localhost:3000/Students")
			.then().statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("course[0]", equalTo("c"))
			.body("course[1]", equalTo("c++"))
			.header("content-Type", "application/json; charset=utf-8")
			.log().all();
			
		}
	
	
	//@Test (priority=1)
	void testPostUsingJsonLibrary() {
		
		JSONObject data=new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		String courseArr[]= {"c","c++"};
		data.put("course", courseArr);
		given().contentType("application/json").body(data)
		.when().post("http://localhost:3000/Students")
		.then().statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("course[0]", equalTo("c"))
		.body("course[1]", equalTo("c++"))
		.header("content-Type", "application/json; charset=utf-8")
		.log().all();
		
	}
	
	//@Test (priority=1)
	void testPostUsingPojo() {
		
		Pojo_PostRequest data=new Pojo_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		String courseArr[]= {"c","c++"};
		data.setCourses(courseArr);
		given().contentType("application/json").body(data)
		.when().post("http://localhost:3000/Students")
		.then().statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("c"))
		.body("courses[1]", equalTo("c++"))
		.header("content-Type", "application/json; charset=utf-8")
		.log().all();
		
	}
	
	@Test (priority=1)
	void testPostUsingExternalJSONFile() throws FileNotFoundException {
		
		File f=new File(".\\body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		given().contentType("application/json").body(data.toString())
		.when().post("http://localhost:3000/Students")
		.then().statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("c"))
		.body("courses[1]", equalTo("c++"))
		.header("content-Type", "application/json; charset=utf-8")
		.log().all();
		
	}
	@Test (priority=2)
	void testDelete() {
		given().when().delete("http://localhost:3000/Students/4")
		.then().statusCode(200);
	}

}
