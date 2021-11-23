package io.platformbuilders.clientAPI.utils;

import static io.restassured.RestAssured.given;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.response.Response;

public class Login {

	public String authenticationAdminUser() throws JSONException {

		Response response = given().auth().preemptive().basic("clientAPI", "")
				.contentType("application/x-www-form-urlencoded").log().all().formParam("grant_type", "password")
				.formParam("username", "admin_user").formParam("password", "Casewise32").when()
				.post("http://localhost:8081/auth/realms/clientAPI/protocol/openid-connect/token");

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		String tokenType = jsonObject.get("token_type").toString();

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(tokenType);
		stringBuilder.append(" ");
		stringBuilder.append(accessToken);

		return stringBuilder.toString();
	}

	public String authenticationCommomUser() throws JSONException {

		Response response = given().auth().preemptive().basic("clientAPI", "")
				.contentType("application/x-www-form-urlencoded").log().all().formParam("grant_type", "password")
				.formParam("username", "commom_user").formParam("password", "Casewise32").when()
				.post("http://localhost:8081/auth/realms/clientAPI/protocol/openid-connect/token");

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		String tokenType = jsonObject.get("token_type").toString();

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(tokenType);
		stringBuilder.append(" ");
		stringBuilder.append(accessToken);

		return stringBuilder.toString();
	}
}
