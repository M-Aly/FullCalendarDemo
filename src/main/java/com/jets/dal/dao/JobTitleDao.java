package com.jets.dal.dao;

import com.jets.dal.entity.JobTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Dao for manipulating JobTitle Entity
 * @author M. ALI
 * @author Hamada Abdrabou
 */
@Repository
public interface JobTitleDao extends CrudRepository<JobTitle, byte[]> {

}
