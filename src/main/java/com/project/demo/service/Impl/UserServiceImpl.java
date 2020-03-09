package com.project.demo.service.Impl;

import com.project.demo.DO.UserDO;
import com.project.demo.dao.UserDOMapper;
import com.project.demo.error.BusinessException;
import com.project.demo.error.EmBusinessErr;
import com.project.demo.service.UserService;
import com.project.demo.utils.MD5Util;
import com.project.demo.utils.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/2 13:15
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDOMapper userDOMapper;

    public UserServiceImpl(UserDOMapper userDOMapper) {
        this.userDOMapper = userDOMapper;
    }

    @Override
    public String login(String tel, String password) throws BusinessException {
        try {
            String realPsd = MD5Util.getMD5(password);
            UserDO userDO = userDOMapper.selectByTel(tel);
            if (userDO != null) {
                if (!tel.equals(userDO.getTel()) && password.equals(realPsd)) {
                    throw new BusinessException(EmBusinessErr.USER_LOGIN_ERROR);
                } else {
                    MySessionUtil.getSession().setAttribute(MySessionUtil.USER_ID, userDO);
                    return userDO.getUserName();
                }
            } else {
                throw new BusinessException(EmBusinessErr.USER_LOGIN_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.USER_LOGIN_ERROR);
        }
    }

    @Override
    public void registered(String userName, String tel, String password) throws BusinessException {
        try {
            UserDO userDO = userDOMapper.selectByTel(tel);
            if (userDO != null) {
                throw new BusinessException(EmBusinessErr.USER_ACCOUNT_EXISTS_ALREADY);
            } else {
                String realPsd = MD5Util.getMD5(password);
                userDOMapper.registered(userName, tel, realPsd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.USER_REGISTERED_ERROR);
        }
    }


}
