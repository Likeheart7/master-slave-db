package com.chenx.controller;

import com.chenx.entity.User;
import com.chenx.service.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoServiceImpl demoService;

    @GetMapping("/demo")
    public void demo() {
        System.out.println(demoService.select());
        demoService.insert(new User("chen", 21));
    }
}
