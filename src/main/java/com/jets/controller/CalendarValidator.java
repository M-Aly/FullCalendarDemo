package com.jets.controller;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jets.dal.entity.Event;

@Component
public class CalendarValidator implements Validator {
	
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

}
