package com.example.jpastudy;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JpaStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaStudyApplication.class, args);
    }

    @Controller
    public static class MainController {

        @GetMapping("/")
        public String main(Map<String, Object> model) {
            model.put("name", "Guest");
            return "/index";
        }
    }
}
