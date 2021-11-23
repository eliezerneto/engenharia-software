package io.platformbuilders.clientAPI.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.Map;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import io.platformbuilders.clientAPI.data.Data;
import io.platformbuilders.clientAPI.utils.BaseTestApi;
import io.platformbuilders.clientAPI.utils.Login;
import io.restassured.http.ContentType;

class ClientApiTests extends BaseTestApi {

	@Test
	void createValidClientAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();
		Map client = Data.createValidClient();
		given().header("Authorization", tokenAdmin).body(client).contentType(ContentType.JSON).post("v1/clients").then()
				.log().all().statusCode(200);
	}

	@Test
	void createInvalidClientAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();
		Map client = Data.createInvalidClient();
		given().header("Authorization", tokenAdmin).body(client).contentType(ContentType.JSON).post("v1/clients").then()
				.log().all().statusCode(400);
	}

	@Test
	void createValidClientCommomUser_403() throws JSONException {
		String tokenCommomUser = new Login().authenticationCommomUser();
		Map client = Data.createValidClient();
		given().header("Authorization", tokenCommomUser).body(client).contentType(ContentType.JSON).post("v1/clients")
				.then().log().all().statusCode(403);
	}

	@Test
	void createInvalidClientCommomUser_403() throws JSONException {
		String tokenCommomUser = new Login().authenticationCommomUser();
		Map client = Data.createInvalidClient();
		given().header("Authorization", tokenCommomUser).body(client).contentType(ContentType.JSON).post("v1/clients")
				.then().log().all().statusCode(403);
	}

	@Test
	void listAllClientsAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients").then().log().all().statusCode(200);
	}

	@Test
	void listAllClientsCommomUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationCommomUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients").then().log().all().statusCode(200);
	}

	@Test
	void listClientsByNameAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients?name=Eliezer").then().log().all()
				.statusCode(200).body(containsString("Eliezer"));
	}

	@Test
	void listClientsByNameAdminUser_404() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients?name=Teste").then().log().all()
				.statusCode(404);
	}

	@Test
	void listClientsByBirthDateAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients?dateAfter=01-01-1990&dateBefore=31-12-1990")
				.then().log().all().statusCode(200).body(containsString("Eliezer"));
	}

	@Test
	void listClientsByBirthDateAdminUser_400() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients?dateAfter=01-01-2000&dateBefore=31-12-2000")
				.then().log().all().statusCode(404);
	}

	@Test
	void listClientsByIdAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients/6").then().log().all().statusCode(200)
				.body(containsString("Eliezer"));
	}

	@Test
	void listClientsByIdAdminUser_400() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().get("v1/clients/1").then().log().all().statusCode(404);
	}

	@Test
	void updateValidClientAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();
		Map client = Data.createValidClient();
		given().header("Authorization", tokenAdmin).body(client).contentType(ContentType.JSON).put("v1/clients/6")
				.then().log().all().statusCode(200);
	}

	@Test
	void updateParcialClientAdminUser_200() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();
		Map client = Data.createInvalidClient();
		given().header("Authorization", tokenAdmin).body(client).contentType(ContentType.JSON).put("v1/clients/6")
				.then().log().all().statusCode(200);
	}

	@Test
	void updateValidClientCommomUser_403() throws JSONException {
		String tokenCommomUser = new Login().authenticationCommomUser();
		Map client = Data.createValidClient();
		given().header("Authorization", tokenCommomUser).body(client).contentType(ContentType.JSON).put("v1/clients/6")
				.then().log().all().statusCode(403);
	}

	@Test
	void updateParcialClientCommomUser_403() throws JSONException {
		String tokenCommomUser = new Login().authenticationCommomUser();
		Map client = Data.createInvalidClient();
		given().header("Authorization", tokenCommomUser).body(client).contentType(ContentType.JSON).put("v1/clients/6")
				.then().log().all().statusCode(403);
	}

	@Test
	void deleteInvalidClientsByIdAdminUser_400() throws JSONException {
		String tokenAdmin = new Login().authenticationAdminUser();

		given().header("Authorization", tokenAdmin).when().delete("v1/clients/1").then().log().all().statusCode(404);
	}

	@Test
	void deleteValidClientsByIdCommomUser_403() throws JSONException {
		String tokenCommomUser = new Login().authenticationCommomUser();

		given().header("Authorization", tokenCommomUser).when().delete("v1/clients/6").then().log().all()
				.statusCode(403);
	}

	@Test
	void deleteInvalidClientsByIdCommomUser_403() throws JSONException {
		String tokenCommomUser = new Login().authenticationCommomUser();

		given().header("Authorization", tokenCommomUser).when().delete("v1/clients/1").then().log().all()
				.statusCode(403);
	}

}
