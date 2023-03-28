package com.learning.userservice.service.impl;

import com.learning.userservice.dao.LoginDao;
import com.learning.userservice.model.CustomerRequest;
import com.learning.userservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Override
    public void registerUser(CustomerRequest customerRequest) {
        loginDao.registerUserDao(customerRequest);
    }
}
