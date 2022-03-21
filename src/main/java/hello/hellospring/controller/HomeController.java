package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")//그냥 8080 들어오면 나올 화면
    public String home() {
        return "home";
    }
}

