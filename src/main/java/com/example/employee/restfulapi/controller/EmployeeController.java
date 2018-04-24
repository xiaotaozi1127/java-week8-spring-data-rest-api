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

    @GetMapping("/employees/gender/{gender}")
    public List<Employee> GetEmployeesByGender(@PathVariable String gender){
        return employeeRepository.findByGender(gender);
    }

    @PostMapping("/employees")
    public Employee CreateNewEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.PUT)
    public Employee UpdateEmployee(@PathVariable long employeeId, @RequestBody Employee employee){
        Employee selectedEmployee = employeeRepository.findOne(employeeId);
        selectedEmployee.setAge(employee.getAge());
        selectedEmployee.setCompanyId(employee.getCompanyId());
        selectedEmployee.setGender(employee.getGender());
        selectedEmployee.setName(employee.getName());
        selectedEmployee.setSalary(employee.getSalary());
        employeeRepository.save(selectedEmployee);
        return selectedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity DeleteEmployee(@PathVariable long employeeId){
        if(employeeRepository.exists(employeeId)){
            employeeRepository.delete(employeeId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("cannot find such employeeId", HttpStatus.BAD_REQUEST);
    }
}
