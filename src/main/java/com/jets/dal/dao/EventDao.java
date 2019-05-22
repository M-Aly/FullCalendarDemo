package com.jets.dal.dao;

import com.jets.dal.entity.Event;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * DAO for Crud Operation for Event Entity
 * @author M. ALI
 * @author Hamada Abdrabou
 */
@Repository
public interface EventDao extends CrudRepository<Event, UUID> {
	
	List<Event> findByStartDateNullOrEndDateNull();

}
