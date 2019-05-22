package com.jets.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * convert objects to json
 * @author M. ALI
 */
public class JsonAdapter {
	
	private ObjectMapper objectMapper;
	
	public JsonAdapter() {
		objectMapper = new ObjectMapper();
	}
	
	/**
	 * convert object to json
	 * @param object object to convert to json
	 * @return json
	 * @author M. ALI
	 */
	public String convertToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
