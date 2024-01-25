package com.example.adrbook.apicontroller;

import com.example.adrbook.dto.LoadData;
import com.example.adrbook.utility.LoadDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/utils")
public class OptionController {

    @Autowired
    private LoadDataManager loadDataManager;

    @GetMapping(value = "/heartbeat", produces = "application/json")
    public String getAllDepartments() {
        return "OK";
    }

    @PutMapping(value = "/load")
    public String loadData(@RequestBody LoadData loadData){
        return loadDataManager.load(loadData);
    }

}
