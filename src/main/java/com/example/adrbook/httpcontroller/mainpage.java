package com.example.adrbook.httpcontroller;

import com.example.adrbook.service.DepartmentService;
import com.example.adrbook.service.PersonService;
import com.example.adrbook.service.ServiceInfoService;
import com.example.adrbook.utility.MyCounter;
import com.example.adrbook.utility.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@ControllerAdvice
public class mainpage {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private SearchHelper searchHelper;

    @ModelAttribute("requestParams")
    public Map<String,String[]> contextPath(final HttpServletRequest request) {
        return request.getParameterMap();
    }
    @GetMapping(value = {"/", "/main"})
    public String viewHomePage(Model model, @RequestParam(name="searchtext", required = false) String searchtext, @RequestParam(name="search-value-type", required = false) String searchValueType) {
        MyCounter myCounter = new MyCounter();
        myCounter.setCounter(0L);
        model.addAttribute("myCounter", myCounter);
        model.addAttribute("updateDate", serviceInfoService.getUpdateData("HH:mm dd.MM.yyyy"));
        model.addAttribute("searchList", searchHelper.getSearchTags());
        model.addAttribute("searchHelper", searchHelper);
        if(searchtext!=null && !searchtext.isEmpty()){
            searchtext = searchtext.replace("\u00a0"," "); //replace &nbsp
            searchtext = searchtext.trim(); //trim white spaces
            if(StringUtils.isEmpty(searchValueType) || StringUtils.equals(searchValueType, "person")) {
                model.addAttribute("departmentList", departmentService.getDepartmentListWithEmployees(searchtext));
            } else if (StringUtils.equals(searchValueType, "department")) {
                model.addAttribute("departmentList", departmentService.getDepartmentListByNameWithEmployees(searchtext));
            }
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
