package edu.duan.app.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexKanban {
    @GetMapping("/hello")
    public String helloKanban() {
        return "index";
    }
}
