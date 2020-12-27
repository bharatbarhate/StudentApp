package com.studentapp.tests;


import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.studentapp.mocel.StudentPojo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class MyFirstTestCorrectedFramewrok extends TestBase{
	private void styles() {
		/*
//Way 1 		
		RestAssured.given()
		.queryParam("", "") //1. Prepare REQUEST from query parameters 
		.when()
		.get("https://www.google.com/")
		//2. perform http operations like get Post put etc
		.then();
		//3 Once you get a response form when then validate;

		//Way 2
		RestAssured.given()
		.expect()
		.when();

		 */
	}
	@DisplayName("Get All Students form Database")
	@Test
	void getAllStudents_NonBDD() {
		RequestSpecification requestSpecification= RestAssured.given();
		Response response = requestSpecification.get("/list");

		//response.prettyPrint();
		ValidatableResponse validatableresponse = response.then();
		validatableresponse.statusCode(200);
	}
	@DisplayName("Get All Students form Database in BDD Way")
	@Test
	void getAllStudents_BDD() {
		RestAssured.given()
		.when()
		.get("/list")
		//.prettyPrint()
		.then()
		.statusCode(200);
	}

	@DisplayName("Get all Computer science students with Query Parameter & ,get result of 2 records")
	@Test
	void getCSStudents() {
		Response response = RestAssured.given()
				//		.queryParam("programme", "Computer Science")
				//		.queryParam("limit", "2")
				.queryParams("programme", "Computer Science", "limit", "1")
				.when()
				.get("/list");
		//"http://localhost:9090/student/list?programme=Computer%20Science&limit=2"

		response.prettyPrint();
		//.assertThat().
		//body(contains email("tincidunt.dui@ultricessit.co.uk")).
		//body(contains email("nascetur@conguea.com"));

	}

	@DisplayName("Get all Computer science students with Query Parameter & get result of 2 records")
	@Test
	void getCSStudentsViaMap() {

		HashMap<String, Object>queryparams = new HashMap<String, Object>();
		queryparams.put("programme", "Computer Science");
		queryparams.put("limit", 10);

		Response response = RestAssured.given()
				//		.queryParam("programme", "Computer Science")
				//		.queryParam("limit", "2")
				.queryParams(queryparams)
				.when()
				.get("/list");
		//"http://localhost:9090/student/list?programme=Computer%20Science&limit=2"

		response.prettyPrint();
		//.assertThat().
		//body(contains email("tincidunt.dui@ultricessit.co.uk")).
		//body(contains email("nascetur@conguea.com"));

	}

	@DisplayName("Path Parameter example, get 1st student or specific student by id")
	@Test
	void getFirstStudentUsingPathParam() {

		Response response = RestAssured.given()
				.pathParam("id", "2")
				.when()
				.get("/{id}");
		response.prettyPrint();
	}

	@DisplayName("Create a new student by sending payload as String")
	@Test
	void createNewStudentStringTypePayload() {
		String payload = "{\"firstName\":\"ffsdsdf\",\"lastName\":\"sdfsdfsdf\",\"email\":\"123hhh@ddd.net\",\"programme\":\"Technical Analysis\",\"courses\":[\"Accounting\",\"Statistics\"]}";
		RestAssured.given()
				.when()
				.contentType(ContentType.JSON)
				.and()
				.body(payload) //Passing payload to body as String
				.post()
				.then()
				.statusCode(201);
	}
	
	@DisplayName("Create a new student by sending payload as object")
	@Test
	void createNewStudentPOJOPayload() {
		
		StudentPojo student = new StudentPojo();
		
		java.util.List<String> courses =  new ArrayList<String>();
		courses.add("Software Testing");
		courses.add("Data Science");
		
		student.setFirstName("Bharu");
		student.setLastName("Barhate");
		student.setEmail("bb@bb123.com");
		student.setProgramme("Production Engineering");
		student.setCourses(courses);
		RestAssured.given()
				.when()
				.contentType(ContentType.JSON)
				.and()
				.body(student) //Passing payload to body as String
				.post()
				.then()
				.statusCode(201);
	}
	
	@DisplayName("Create a new student by sending payload as object via Faker API")
	@Test
	void createNewStudentPOJOPayloadFakerAPI() {
		
		StudentPojo student = new StudentPojo();
		
		Faker fake = new Faker();
		java.util.List<String> courses =  new ArrayList<String>();
		courses.add("Software Testing");
		courses.add("Data Science");
		
		student.setFirstName(fake.name().firstName());
		student.setLastName(fake.name().lastName());
		student.setEmail(fake.internet().emailAddress());
		student.setProgramme("Computer Engineering");
		student.setCourses(courses);
		RestAssured.given()
				.when()
				.contentType(ContentType.JSON)
				.and()
				.body(student) //Passing payload to body as String
				.post()
				.then()
				.statusCode(201);
	}
}
