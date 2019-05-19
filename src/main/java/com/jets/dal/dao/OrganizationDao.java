package com.jets.dal.dao;

import com.jets.dal.entity.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @description("Dao for Crud Operation with Organization Entity")
 * 
 * @author Mohamed Ali, Hamada Abdrabou, Mohamed Jamal
 */
@Repository
public interface OrganizationDao extends CrudRepository<Organization, byte[]> {

}
