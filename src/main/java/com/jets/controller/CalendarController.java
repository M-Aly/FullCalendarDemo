package com.jets.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.jets.dal.dao.EventDao;
import com.jets.dal.dao.JobTitleDao;
import com.jets.dal.dao.OrganizationDao;
import com.jets.dal.dao.SystemUserDao;
import com.jets.dal.entity.Event;
import com.jets.dal.entity.JobTitle;
import com.jets.dal.entity.Organization;
import com.jets.dal.entity.SystemUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description MVC Controller for Adding, Removing and Displaying Events and Calendar
 * @author Mohamed Ali, Hamada Abdrabou, Mohamed Jamal
 */
@Controller
public class CalendarController {

    @Autowired
    private EventDao eventDao;
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private JobTitleDao jobTitleDao;
    @Autowired
    private SystemUserDao systemUserDao;
    
    /**
     * @description Displaying Calender and Scheduled Events
     * @return ModelAndView Object Containing All Calender Events and Logical View Name
     */
    @RequestMapping(value="/displayCalendar", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView displayCalender(){
        List<Event> events = new ArrayList<>();
        eventDao.findAll().forEach(event -> events.add(DummyEvent.getEvent(event)));
        ModelAndView model = new ModelAndView("display_calendar");
        Gson gson = new Gson();
        model.addObject("events", gson.toJson(events));
        return model;
    }
    /**
     * @description Get method for returning html form for adding new events
     * @param model contains the current event
     * @return Logical name for success view 
     */
    @RequestMapping(value="/addEvent", method=RequestMethod.GET)
    public String addEvent(Model model){
        model.addAttribute("event", new Event());
        return "add_event";
    }
    
    /**
     * @description Post method handler for adding new events
     * @return Logical name for success view 
     */
    @RequestMapping(value="/addEvent", method=RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event calendarEvent, Model model){
        Event event = DummyEvent.getEvent(calendarEvent);
        Iterator<Organization> organizationIterator = organizationDao.findAll().iterator();
        if(organizationIterator.hasNext()) {
            event.setOrganization(organizationIterator.next());
        }
        else {
            organizationDao.save(event.getOrganization());
        }
        Iterator<JobTitle> jobTitleIterator = jobTitleDao.findAll().iterator();
        if(jobTitleIterator.hasNext()) {
            event.getSystemUser().setJobTitle(jobTitleIterator.next());
        }
        else {
            jobTitleDao.save(event.getSystemUser().getJobTitle());
        }
        Iterator<SystemUser> systemUserIterator = systemUserDao.findAll().iterator();
        if(systemUserIterator.hasNext()) {
            event.setSystemUser(systemUserIterator.next());
        }
        else {
            systemUserDao.save(event.getSystemUser());
        }
        eventDao.save(event);
        return "redirect:displayCalendar.htm";
    }
    
    @RequestMapping(value="/editEvent", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView editEvent(@RequestParam("eventId") byte[] eventId){
        Optional<Event> optionalEvent = eventDao.findById(eventId);
        Event event = null;
        if(optionalEvent.isPresent())
            event = optionalEvent.get();
        Gson gson = new Gson();
        String jsonEvent = gson.toJson(event);
        ModelAndView model = new ModelAndView("edit_event", "event", jsonEvent);
        return model;
    }
    @RequestMapping(value="/editEvent", method=RequestMethod.POST)
    @ResponseBody
    public String editEvent(@ModelAttribute("event") Event event){
        eventDao.save(event);
        return "edit_event";
    }
    @RequestMapping(value="/deleteEvent", method=RequestMethod.POST)
    @ResponseBody
    public String deleteEvent(@RequestParam("eventId") String eventId){
        System.out.println(eventId);
        Event event = eventDao.findById(eventId.getBytes()).get();
        System.out.println("The Event"+event.getUuid()+"Will Be Deleted");
        eventDao.deleteById(eventId.getBytes());
        return "display_calender";
    }
    
    /**
     * @description Used for mapping Date Objects from submitted forms to java.util.Date
     * @param binder used to register editors
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customEditor);
    }

}
