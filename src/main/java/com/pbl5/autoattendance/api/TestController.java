package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String sayhello(){
        Test test = new Test();
        test.setName("hello");
        System.out.println(test.getName());
        return "hello";
    }
}
