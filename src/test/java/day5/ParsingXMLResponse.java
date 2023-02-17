package day5;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ParsingXMLResponse {
	
	//@Test
	void testXMLResponse() {
		given().when().get("http://restapi.adequateshop.com/api/Traveler")
		.then().statusCode(200)
		.header("Content-Type", "application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page", equalTo("1"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		
	}
	
	//@Test
	void testXMLResponse1() {
		Response res=given().when().get("http://restapi.adequateshop.com/api/Traveler");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/xml; charset=utf-8");
		String pageno=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageno, "1");
		String travellername=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travellername, "Developer");
	}
	
	@Test
	void testXMLResponse2() {
		Response res=given().when().get("http://restapi.adequateshop.com/api/Traveler");
		XmlPath xmlobj=new XmlPath(res.asString());
		List <String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(),10);
		List <String> traveller_names=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean status=false;
		for(String travellername:traveller_names) {
			//System.out.println(travellername);
			if(travellername.equals("Developer")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		}

}
