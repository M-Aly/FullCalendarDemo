package com.jets.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jets.adapter.JsonAdapter;
import com.jets.controller.dto.EventJsonResponse;
import com.jets.dal.entity.Event;
import com.jets.service.EventService;

/**
 * MVC Controller for Adding, Removing and Displaying events
 * @author M. ALI
 * @author Hamada Abdrabou
 */
@RestController
public class EventController {
	
	JsonAdapter jsonAdapter = new JsonAdapter();
	
	@Autowired
	private EventService eventService;
	
	private void setErrors(BindingResult result, EventJsonResponse response) {
		Map<String, String> errors = result.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		response.setValidated(false);
		response.setErrorMessages(errors);
	}

	/**
	 * add new event
	 * @param event event to add to database
	 * @param result
	 * @return json event
	 * @author M. ALI
	 * @author Hamada Abdrabou
	 */
	@RequestMapping(value = "/addEvent", method=RequestMethod.POST)
	public @ResponseBody
	String saveEvent(@RequestBody @Valid Event event, BindingResult result) {
		EventJsonResponse response = new EventJsonResponse();
		if (result.hasErrors()) {
			setErrors(result, response);
		}
		else {
			eventService.saveCalendarEvent(event);
			response.setValidated(true);
			response.setEvent(event);
		}
		return jsonAdapter.convertToJson(response);
	}
	
	/**
	 * add new event
	 * @param event event to add to database
	 * @param result
	 * @return json event
	 * @author M. ALI
	 * @author Hamada Abdrabou
	 */
	@RequestMapping(value="/editEvent", method=RequestMethod.POST)
    public @ResponseBody
    String editEvent(@RequestBody @Valid Event event, BindingResult result) {
		EventJsonResponse response = new EventJsonResponse();
		if (result.hasErrors()) {
			setErrors(result, response);
		}
		else {
	        eventService.updateCalendarEvent(event);
			response.setValidated(true);
			response.setEvent(event);
		}
		return jsonAdapter.convertToJson(response);
    }
	
	/**
	 * get event data in form
	 * @param eventId id of event
	 * @return json event
	 * @author M. ALI
	 * @author Hamada Abdrabou
	 */
	@RequestMapping(value="/getEvent", method=RequestMethod.GET)
    public @ResponseBody
    String editEvent(@RequestParam("eventId") String eventId) {
        Event event = eventService.getEventById(eventId);
        return jsonAdapter.convertToJson(event);
    }
    
	/**
	 * delete event
	 * @param eventId id of event
	 * @return event id
	 * @author M. ALI
	 * @author Hamada Abdrabou
	 */
    @RequestMapping(value="/deleteEvent", method=RequestMethod.POST)
    public @ResponseBody
    String deleteEvent(@RequestParam("eventId") String eventId){
        Event event = eventService.getEventById(eventId);
        System.out.println("The Event"+event.getUuid()+"Will Be Deleted");
        eventService.deleteCalendarEvent(eventId);
        return eventId;
    }

}
