package com.example.adrbook.utility;

import com.example.adrbook.dto.DepartmentData;

import java.util.HashMap;
import java.util.Map;

public class MyCounter {
    private long counter;
    private Map<Long, String> departmentSequence;

    public MyCounter() {
        departmentSequence = new HashMap<>();
    }

    public void setCounter(Long c) {
        counter = c;
    }
    public Long getCounter() {
        return counter;
    }
    public Long incrementAndGet(){
        return ++counter;
    }
    public String getDepartmentClass(DepartmentData d){
        return "departmentId" + d.getId();
    }

}
