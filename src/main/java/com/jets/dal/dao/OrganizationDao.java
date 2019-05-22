package com.jets.dal.dao;

import com.jets.dal.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Dao for Crud Operation with Organization Entity
 * @author M. ALI
 * @author Hamada Abdrabou
 */
@Repository
public interface OrganizationDao extends CrudRepository<Organization, byte[]> {

}
