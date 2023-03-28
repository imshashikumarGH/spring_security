package com.learning.userservice.dao.impl;

import com.learning.userservice.dao.LoginDao;
import com.learning.userservice.model.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class LoginDaoImpl implements LoginDao {



    @Override
    public void registerUserDao(CustomerRequest customerRequest) {
    }
}
