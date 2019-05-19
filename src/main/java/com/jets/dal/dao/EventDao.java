package com.jets.dal.dao;

import com.jets.dal.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends CrudRepository<Event, byte[]> {

}
