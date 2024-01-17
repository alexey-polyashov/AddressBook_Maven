package com.example.adrbook.apicontroller;

import com.example.adrbook.dto.LoadData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/utils")
public class OptionController {

    @GetMapping(value = "/heartbeat", produces = "application/json")
    public String getAllDepartments() {
        return "OK";
    }

    @PutMapping(value = "/load")
    public String loadData(@RequestBody LoadData loadData){

        return "loaded";
    }

}
