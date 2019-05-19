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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value="/addEvent", method=RequestMethod.GET)
    public String addEvent(Model model){
        model.addAttribute("event", new Event());
        return "add_event";
    }

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
        return "display_calendar";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Event.class);
    }

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

    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, customEditor);
    }

}
