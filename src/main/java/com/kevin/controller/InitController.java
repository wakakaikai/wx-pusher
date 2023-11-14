package com.kevin.controller;

import com.kevin.service.InitService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author: Kevin
 * @Date: 2022/8/29 19:12
 * @Description: 初始化需要获取的一些数据
 */
@RestController
public class InitController {
    @Resource
    private InitService initService;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 初始化订阅者列表
        initService.initSubscriberList();
        // 初始化自定义菜单
//        initService.initCustomMenu();
    }


}
