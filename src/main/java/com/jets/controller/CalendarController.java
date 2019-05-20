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
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
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
 * @description("MVC Controller for Adding, Removing and Displaying Events and Calender")
 * 
 * @author Mohamed Ali, Hamada Abdrabou, Mohamed Jamal
 */
@Controller
public class CalendarController implements Validator{

    @Autowired
    private EventDao eventDao;
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private JobTitleDao jobTitleDao;
    @Autowired
    private SystemUserDao systemUserDao;
    /**
     * @param
     * @description("Displays The Calender with attached events")
     * @return ModelAndView Object Containing All Calender Events and Logical View Name
     */
//    Displaying Calender and Scheduled Events
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
     * @param
     * @description("Get method for returning html form for adding new events")
     * @return Logical name for success view 
     */
    @RequestMapping(value="/addEvent", method=RequestMethod.GET)
    public String addEvent(Model model){
        model.addAttribute("event", new Event());
        return "add_event";
    }
    
    /**
     * @param
     * @description("Post method handler for adding new events")
     * @return Logical name for success view 
     */
    @RequestMapping(value="/addEvent", method=RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event1, Model model){
        Event event = DummyEvent.getEvent(event1);
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
    public ModelAndView editEvent(@RequestParam("eventId") String eventId){
        Optional<Event> optionalEvent = eventDao.findById(UUID.fromString(eventId));
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
        Event event = eventDao.findById(UUID.fromString(eventId)).get();
        System.out.println("The Event"+event.getUuid()+"Will Be Deleted");
        eventDao.deleteById(UUID.fromString(eventId));
        return eventId;
    }
    /**
     * @param
     * @description("Override Method from spring validator interface to ensure the type of Event")
     * @return 
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Event.class);
    }
    /**
     * @param
     * @description("Validating Date and Title for The Event")
     * @return 
     */
    @Override
    public void validate(Object object, Errors errors) {
        Event event = (Event) object;
        Date date = new Date();
        if(event.getStartDate().compareTo(date) > 0){
            errors.rejectValue("startDate", "Invalid Start Date", "Invalid Start Date");
        }
        if(event.getStartDate().compareTo(date) > 0){
            errors.rejectValue("endDate", "Invalid End Date", "Invalid End Date");
        }
        ValidationUtils.rejectIfEmpty(errors, "title", "required.title", "Title is required");
    }
    /**
     * @param WebDataBinder
     * @description("Used for mapping Date Objects from submitted forms to java.util.Date")
     * @return nothing
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customEditor);
    }

}
