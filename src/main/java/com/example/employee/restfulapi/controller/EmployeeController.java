package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //在此处完成Employee API

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> GetAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee GetEmployeeById(@PathVariable long id){
        return employeeRepository.findOne(id);
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public Page<Employee> GetEmployeesByPage(@PathVariable int page, @PathVariable int pageSize){
        return employeeRepository.findAll(new PageRequest(page, pageSize));
    }

    @GetMapping("/gender/{gender}")
    public List<Employee> GetEmployeesByGender(@PathVariable String gender){
        return employeeRepository.findByGender(gender);
    }

    @PostMapping
    public Employee CreateNewEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> UpdateEmployee(@PathVariable long employeeId, @RequestBody Employee employee){
        if(employeeRepository.exists(employeeId)){
            Employee selectedEmployee = employeeRepository.findOne(employeeId);
            selectedEmployee.setAge(employee.getAge());
            selectedEmployee.setCompanyId(employee.getCompanyId());
            selectedEmployee.setGender(employee.getGender());
            selectedEmployee.setName(employee.getName());
            selectedEmployee.setSalary(employee.getSalary());
            employeeRepository.save(selectedEmployee);
            return new ResponseEntity<>(selectedEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity DeleteEmployee(@PathVariable long employeeId){
        if(employeeRepository.exists(employeeId)){
            employeeRepository.delete(employeeId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("cannot find such employeeId", HttpStatus.BAD_REQUEST);
    }
}
