package com.example.demospringboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @GetMapping("/help-check")
    @ResponseBody
    public String helpCheck() {
        return "Staff Example Help Check";
    }
}
