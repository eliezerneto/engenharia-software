package io.platformbuilders.clientAPI.utils;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

import org.junit.jupiter.api.BeforeAll;

public class BaseTestApi {

	@BeforeAll
	public static void preCondition() {
		baseURI = "https://localhost";
		basePath = "/api";
		port = 8443;
		useRelaxedHTTPSValidation();
	}
}
