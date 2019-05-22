package com.jets.controller;

import com.jets.dal.entity.Event;
import com.jets.dal.entity.JobTitle;
import com.jets.dal.entity.Organization;
import com.jets.dal.entity.SystemUser;

/**
 * dummy event data
 * @author M. ALI
 */
public class DummyEvent {

    public static Event getEvent(Event calendarEvent) {

        Organization organization = new Organization();
        organization.setName("iti");
        organization.setDeleted(false);

        JobTitle jobTitle = new JobTitle();
        jobTitle.setName("developer");
        jobTitle.setDeleted(false);

        SystemUser systemUser = new SystemUser();
        systemUser.setUsername("mohamed");
        systemUser.setFirstName("mohamed");
        systemUser.setPassword("password");
        systemUser.setEmail("abc@gmail.com");
        systemUser.setDeleted(false);
        systemUser.setJobTitle(jobTitle);

        Event event = new Event();
        event.setDeleted(false);
        event.setOrganization(organization);
        event.setSystemUser(systemUser);
        event.setUuid(calendarEvent.getUuid());
        event.setName(calendarEvent.getName());
        event.setShortDescription(calendarEvent.getShortDescription());
        event.setAddress(calendarEvent.getAddress());
        event.setStartDate(calendarEvent.getStartDate());
        event.setEndDate(calendarEvent.getEndDate());

        return event;

    }

}
