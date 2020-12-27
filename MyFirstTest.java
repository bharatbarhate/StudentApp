package com.studentapp.tests;


import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sun.javafx.collections.MappingChange.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class MyFirstTest{
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
		Response response = requestSpecification.get("http://localhost:9090/student/list");

		//response.prettyPrint();
		ValidatableResponse validatableresponse = response.then();
		validatableresponse.statusCode(200);
	}
	@DisplayName("Get All Students form Database in BDD Way")
	@Test
	void getAllStudents_BDD() {
		RestAssured.given()
		.when()
		.get("http://localhost:9090/student/list")
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
				.get("http://localhost:9090/student/list");
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
				.get("http://localhost:9090/student/list");
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
				.get("http://localhost:9090/student/{id}");
		response.prettyPrint();
		
	}
}
