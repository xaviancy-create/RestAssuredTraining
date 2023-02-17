package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SerializationandDeserialization {
	
	//@Test
	void convertPojo2json() throws JsonProcessingException {
		studentRequest stupojo=new studentRequest();
		stupojo.setName("scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		String CourseArr[]= {"c","c++"};
		stupojo.setCourses(CourseArr);
		
		ObjectMapper objMapper=new ObjectMapper();
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsondata);
	}
	
	@Test
	
	void convertJson2Pojo() throws JsonMappingException, JsonProcessingException {
		String jsondata="{\r\n"
				+ "  \"name\" : \"scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"c\", \"c++\" ]\r\n"
				+ "}";
		ObjectMapper objMapper=new ObjectMapper();
		studentRequest stupojo=objMapper.readValue(jsondata, studentRequest.class);
		System.out.println(stupojo.getName());
		System.out.println(stupojo.getLocation());
		System.out.println(stupojo.getPhone());
		System.out.println(stupojo.getCourses()[0]);
		System.out.println(stupojo.getCourses()[1]);
		
	}

}
