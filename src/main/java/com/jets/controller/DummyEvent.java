package com.jets.controller;

import com.jets.dal.entity.Event;
import com.jets.dal.entity.JobTitle;
import com.jets.dal.entity.Organization;
import com.jets.dal.entity.SystemUser;



/**
 * @description("Class for Generating an Event Dummy related Entities")
 * 
 * @author Mohamed Ali, Hamada Abdrabou, Mohamed Jamal
 */
public class DummyEvent {

    public static Event getEvent(Event event1) {

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
        event.setUuid(event1.getUuid());
        event.setName(event1.getName());
        event.setShortDescription(event1.getShortDescription());
        event.setAddress(event1.getAddress());
        event.setStartDate(event1.getStartDate());
        event.setEndDate(event1.getEndDate());

        return event;

    }

}
