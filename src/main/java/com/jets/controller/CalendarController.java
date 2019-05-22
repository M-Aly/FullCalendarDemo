package com.jets.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jets.dal.entity.Event;
import com.jets.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * MVC Controller for Displaying Calendar
 * @author M. ALI
 * @author Hamada Abdrabou
 */
@Controller
@Scope("session")
public class CalendarController {
	
	@Autowired
	private EventService eventService;
    
    /**
     * @description Displaying Calender and Scheduled Events
     * @return ModelAndView Object Containing All Calender Events and Logical View Name
     */
    @RequestMapping(value="/displayCalendar", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView displayCalender(){
        List<Event> events = eventService.getAllEvents();
        ModelAndView model = new ModelAndView("display_calendar");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			model.addObject("events", objectMapper.writeValueAsString(events));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return model;
    }
    
    /**
     * Used for mapping Date Objects from submitted forms to java.util.Date
     * @param binder used to register editors
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customEditor);
    }

}
