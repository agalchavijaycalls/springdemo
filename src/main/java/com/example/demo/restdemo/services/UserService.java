package com.example.demo.restdemo.services;

import com.example.demo.restdemo.dao.UserRepository;
import com.example.demo.restdemo.domain.User;
import com.example.demo.restdemo.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        return userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).orElse(null);
    }

}
