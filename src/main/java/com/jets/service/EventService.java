package com.jets.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jets.dal.dao.EventDao;
import com.jets.dal.dao.JobTitleDao;
import com.jets.dal.dao.OrganizationDao;
import com.jets.dal.dao.SystemUserDao;
import com.jets.dal.entity.Event;
import com.jets.dal.entity.JobTitle;
import com.jets.dal.entity.Organization;
import com.jets.dal.entity.SystemUser;

/**
 * Event services
 * @author M. ALI
 */
@Service
public class EventService {
	
	@Autowired
    private EventDao eventDao;
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private JobTitleDao jobTitleDao;
    @Autowired
    private SystemUserDao systemUserDao;
    
    DummyEvent dummyEvent = new DummyEvent();
	
    /**
     * save event in database
     * @param calendarEvent
     * @author M. ALI
     */
	public void saveCalendarEvent(Event calendarEvent) {
		Event event = dummyEvent.getEvent(calendarEvent);
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
	}
	
	/**
	 * update event in database
	 * @param event
	 * @author M. ALI
	 */
	public void updateCalendarEvent(Event event) {
		eventDao.save(event);
	}
	
	/**
	 * delete event
	 * @param event
	 * @author M. ALI
	 */
	public void deleteCalendarEvent(Event event) {
		eventDao.delete(event);
	}
	
	/**
	 * delete event by id
	 * @param eventId
	 * @author M. ALI
	 */
	public void deleteCalendarEvent(String eventId) {
		eventDao.deleteById(UUID.fromString(eventId));
	}
	
	/**
	 * get event by id
	 * @param eventId
	 * @return
	 */
	public Event getEventById(String eventId) {
		Optional<Event> optionalEvent = eventDao.findById(UUID.fromString(eventId));
        Event event = null;
        if(optionalEvent.isPresent()) {
            event = dummyEvent.getEvent(optionalEvent.get());
        }
        return event;
	}
	
	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<>();
        eventDao.findAll().forEach(event -> events.add(dummyEvent.getEvent(event)));
        return events;
	}
	
	public List<Event> getNonAssignedEvents() {
		List<Event> events = new ArrayList<>();
        eventDao.findByStartDateNullAndEndDateNull().forEach(event -> events.add(dummyEvent.getEvent(event)));
        return events;
	}
	
}
