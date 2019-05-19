package com.jets.dal.dao;

import com.jets.dal.entity.SystemUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserDao extends CrudRepository<SystemUser, byte[]> {

}
