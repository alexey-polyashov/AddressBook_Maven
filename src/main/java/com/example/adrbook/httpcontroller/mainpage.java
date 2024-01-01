package com.example.adrbook.httpcontroller;

import com.example.adrbook.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class mainpage {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = {"/", "/main"})
    public String viewHomePage(Model model) {
        model.addAttribute("departmentList", departmentService.getDepartmentList());
        return "main";
    }
    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
