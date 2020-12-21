package com.example.study.controller;

import com.example.study.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @GetMapping("/getMethod")
    public String getRequest() {
        return "HI getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password) {
        System.out.println(id + " " + password);
        return id+password;
    }

    @GetMapping("/getMultiParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public User getParameter(User user) {
        System.out.println(user.getAccount());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        return user;
    }
}
