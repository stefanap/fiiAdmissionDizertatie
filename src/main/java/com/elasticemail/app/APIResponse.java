package com.elasticemail.app;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIResponse<T> {
	
	private static final ObjectMapper mapper; 
	static { 
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
	};
	
	public Boolean success = false;
	public String error = null;
	public T data;
	
	public APIResponse(String response, Class<T> responseType) throws Exception {
		JsonNode root = mapper.readTree(response);
		
		success = root.get("success").asBoolean();
		JsonNode errorJson = root.get("error");
		if (errorJson != null) error = errorJson.asText();
		if (responseType != VoidApiResponse.class && errorJson == null) data = mapper.treeToValue(root.get("data"), responseType);
	};
	
	public static class VoidApiResponse { }
}