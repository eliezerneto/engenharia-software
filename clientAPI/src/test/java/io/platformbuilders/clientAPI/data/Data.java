package io.platformbuilders.clientAPI.data;

import java.util.HashMap;
import java.util.Map;

public class Data {

	public static Map createValidClient() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Eliezer Tomé de Paula Neto");
		params.put("email", "eliezer.ne@gmail.com");
		params.put("birthDate", "1990-06-06");
		params.put("phone", "123456789");
		return params;
	}

	public static Map createInvalidClient() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Eliezer Tomé de Paula Neto");
		params.put("email", "eliezer.ne@gmail.com");
		return params;
	}
}
