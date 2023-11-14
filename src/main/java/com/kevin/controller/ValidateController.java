package com.kevin.controller;

import com.kevin.service.ValidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Kevin
 * @Date: 2022/8/24 9:41
 * @Description: 验证类的控制器
 */
@RestController
public class ValidateController {
    @Resource
    private ValidateService validateService;

    @GetMapping("/checkToken")
    public String checkToken(HttpServletRequest request, HttpServletResponse response) {
        return validateService.checkToken(request, response);
    }
}
