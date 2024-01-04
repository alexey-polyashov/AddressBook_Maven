package com.example.adrbook.apicontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/utils")
public class OptionController {

    @GetMapping(value = "/heartbeat", produces = "application/json")
    public String getAllDepartments() {
        return "OK";
    }

}
