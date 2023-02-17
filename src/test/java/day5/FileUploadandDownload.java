package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadandDownload {
	
	//@Test
	void singleFileUpload() {
		
		File myfile=new File("C:\\Json\\text1.txt");
		
		given().multiPart("file",myfile).contentType("multipart/form-data")
		.when().post("http://localhost:8080/uploadFile").then().statusCode(200)
		.body("fileName", equalTo("text1.txt")).log().all();
		
	}
	
	//@Test
	void multipleFileUpload() {
		File myfile1=new File("C:\\Json\\text1.txt");
		File myfile2=new File("C:\\Json\\text2.txt");
		given().multiPart("files",myfile1).multiPart("files",myfile2).contentType("multipart/form-data")
		.when().post("http://localhost:8080/uploadMultipleFiles").then().statusCode(200)
		.body("[0].fileName", equalTo("text1.txt")).body("[1].fileName", equalTo("text2.txt")).log().all();
	}
	
	@Test
	void fileDownload() {
		given().when().get("http://localhost:8080/downloadFile/text2.txt")
		.then().statusCode(200).log().all();
		
	}

}
