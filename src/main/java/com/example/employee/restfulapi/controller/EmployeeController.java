package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    //在此处完成Employee API

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> GetAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee GetEmployeeById(@PathVariable long id){
        return employeeRepository.findOne(id);
    }

    @GetMapping("/employees/page/{page}/pageSize/{pageSize}")
    public Page<Employee> GetEmployeesByPage(@PathVariable int page, @PathVariable int pageSize){
        return employeeRepository.findAll(new PageRequest(page, pageSize));
    }
}
