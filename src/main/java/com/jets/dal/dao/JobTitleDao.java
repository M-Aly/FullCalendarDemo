package com.jets.dal.dao;

import com.jets.dal.entity.JobTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleDao extends CrudRepository<JobTitle, byte[]> {

}
