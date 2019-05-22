package com.jets.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/addEvent", method=RequestMethod.POST)
	public @ResponseBody
	String saveEvent(@RequestBody @Valid Event event, BindingResult result) {
		EventJsonResponse response = new EventJsonResponse();
		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			response.setValidated(false);
			response.setErrorMessages(errors);
		}
		else {
			eventService.saveCalendarEvent(event);
			response.setValidated(true);
			response.setEvent(event);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/editEvent", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView editEvent(@RequestParam("eventId") String eventId){
        Event event = eventService.getEventById(eventId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonEvent = null;
		try {
			jsonEvent = objectMapper.writeValueAsString(event);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        ModelAndView model = new ModelAndView("edit_event", "event", jsonEvent);
        return model;
    }
    
    @RequestMapping(value="/editEvent", method=RequestMethod.POST)
    @ResponseBody
    public String editEvent(@ModelAttribute("event") Event event){
        eventService.updateCalendarEvent(event);
        return "edit_event";
    }
    
    @RequestMapping(value="/deleteEvent", method=RequestMethod.POST)
    @ResponseBody
    public String deleteEvent(@RequestParam("eventId") String eventId){
        Event event = eventService.getEventById(eventId);
        System.out.println("The Event"+event.getUuid()+"Will Be Deleted");
        eventService.deleteCalendarEvent(eventId);
        return eventId;
    }

}
