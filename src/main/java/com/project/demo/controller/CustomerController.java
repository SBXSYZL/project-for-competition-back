package com.project.demo.controller;

import com.project.demo.error.BusinessException;
import com.project.demo.response.CommonReturnType;
import com.project.demo.response.RTStr;
import com.project.demo.service.UserService;
import com.project.demo.utils.MySessionUtil;
import com.project.demo.validator.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/2 13:08
 */
@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {
    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonReturnType login(@RequestParam String tel,
                                  @RequestParam String password) throws BusinessException {
        MyValidator.checkTel(tel);
        String login = userService.login(tel, password);
        return CommonReturnType.create(login);
    }

    @PostMapping("/registered")
    public CommonReturnType registered(@RequestParam String userName,
                                       @RequestParam String tel,
                                       @RequestParam String password) throws BusinessException {
        MyValidator.checkTel(tel);
        userService.registered(userName, tel, password);
        return CommonReturnType.create(RTStr.SUCCESS);
    }
    @GetMapping("/logout")
    public void logout(){
        MySessionUtil.getSession().removeAttribute(MySessionUtil.USER_ID);
        MySessionUtil.getSession().removeAttribute(MySessionUtil.SHOP_CART);
    }
}
