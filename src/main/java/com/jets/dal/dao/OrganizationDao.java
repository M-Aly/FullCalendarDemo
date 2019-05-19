package com.jets.dal.dao;

import com.jets.dal.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDao extends CrudRepository<Organization, byte[]> {

}
