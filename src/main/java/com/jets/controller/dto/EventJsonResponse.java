package com.jets.controller.dto;

import java.util.HashMap;
import java.util.Map;

import com.jets.dal.entity.Event;

/**
 * used for ajax responses
 * @author M. ALI
 */
public class EventJsonResponse {
	
	private boolean validated;
	private Map<String, String> errorMessages = new HashMap<>();
	private Event event;
	
	public boolean isValidated() {
		return validated;
	}
	
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}

}
