package com.project.demo.service;

import com.project.demo.error.BusinessException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String login(String tel, String password) throws BusinessException;

    void registered(String userName, String tel, String password) throws BusinessException;
}
