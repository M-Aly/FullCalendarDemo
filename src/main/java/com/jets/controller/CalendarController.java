package com.jets.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jets.adapter.JsonAdapter;
import com.jets.dal.entity.Event;
import com.jets.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * MVC Controller for Displaying Calendar
 * @author M. ALI
 */
@Controller
@Scope("session")
public class CalendarController {
	
	@Autowired
	private EventService eventService;
	
	JsonAdapter jsonAdapter = new JsonAdapter();
    
    /**
     * Displaying Calender and Scheduled Events
     * @param session http session
     * @return ModelAndView Object Containing All Calender Events and Logical View Name
     * @author M. ALI
     */
    @RequestMapping(value="/displayCalendar", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView displayCalender(HttpSession session){
        List<Event> events = eventService.getAllEvents();
        session.setAttribute("events", jsonAdapter.convertToJson(events));
        ModelAndView model = new ModelAndView("display_calendar");
        model.addObject("nonAssignedEvents", eventService.getNonAssignedEvents());
        return model;
    }

}
