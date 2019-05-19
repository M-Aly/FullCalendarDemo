package com.jets.dal.dao;

import com.jets.dal.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @description("DAO for Crud Operation for Event Entity")
 * 
 * @author Mohamed Ali, Hamada Abdrabou, Mohamed Jamal
 */
@Repository
public interface EventDao extends CrudRepository<Event, byte[]> {

}
