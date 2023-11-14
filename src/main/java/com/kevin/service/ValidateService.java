package com.kevin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Kevin
 * @Date: 2022/8/24 18:58
 * @Description: 验证接口定义
 */
public interface ValidateService {
    String checkToken(HttpServletRequest request, HttpServletResponse response);
}
