package io.dowlath.thymeleafcrud.controller;

import io.dowlath.thymeleafcrud.model.Employee;
import io.dowlath.thymeleafcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @Author Dowlath
 * @create 6/1/2020 2:11 AM
 */

@Controller
@Component
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model){
       // model.addAttribute("listOfEmployees",employeeService.getAllEmployees());
       // return "index";
       // return findPaginated(1,model);
        return findPaginated(1,"firstName","asc",model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id,Model model){
        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee",employee);
        return "update_employee";


    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id){
        // call delete employee method
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
   /*
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value= "pageNo") int pageNo,Model model){
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize);
        List<Employee> listOfEmployees = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listOfEmployees",listOfEmployees);
        return "index";

    }
    */

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value= "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Employee> listOfEmployees = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listOfEmployees",listOfEmployees);
        return "index";

    }
}
