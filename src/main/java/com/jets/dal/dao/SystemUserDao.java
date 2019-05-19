package com.jets.dal.dao;

import com.jets.dal.entity.SystemUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @description("For Crud Operation with SystemUser Entity")
 * 
 * @author Mohamed Ali, Hamada Abdrabou, Mohamed Jamal
 */
@Repository
public interface SystemUserDao extends CrudRepository<SystemUser, byte[]> {

}
