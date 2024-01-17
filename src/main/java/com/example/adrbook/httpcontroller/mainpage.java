package com.example.adrbook.httpcontroller;

import com.example.adrbook.service.DepartmentService;
import com.example.adrbook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@ControllerAdvice
public class mainpage {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PersonService personService;

    @ModelAttribute("requestParams")
    public Map<String,String[]> contextPath(final HttpServletRequest request) {
        return request.getParameterMap();
    }
    @GetMapping(value = {"/", "/main"})
    public String viewHomePage(Model model, @RequestParam(name="searchtext", required = false) String searchtext) {
        if(searchtext!=null && !searchtext.isEmpty()){
            model.addAttribute("departmentList", departmentService.getDepartmentListWithEmployees(searchtext));
        }else {
            model.addAttribute("departmentList", departmentService.getDepartmentListWithEmployees(false));
        }
        return "main";
    }

    @GetMapping(value = "/employee/{employeeId}")
    public String viewEmployee(Model model, @PathVariable Long employeeId) {
        model.addAttribute("employee", personService.getExtendedPersonInfo(employeeId));
        return "fragments/employee";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
