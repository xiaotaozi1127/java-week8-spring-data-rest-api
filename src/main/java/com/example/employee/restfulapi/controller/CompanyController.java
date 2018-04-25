package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public List<Company> GetAllCompanies(){
        return companyRepository.findAll();
    }

    @GetMapping("/{companyId}")
    public Company GetCompanyById(@PathVariable long companyId){
        return companyRepository.findById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> GetEmployeesByCompanyId(@PathVariable long companyId){
        return companyRepository.findEmployeesByCompanyId(companyId);
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public Page<Company> GetCompaniesByPage(@PathVariable int page, @PathVariable int pageSize){
        return companyRepository.findAll(new PageRequest(page, pageSize));
    }

    @PostMapping
    public Company CreateNewCompany(@RequestBody Company company){
        return companyRepository.save(company);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity updateCompany(@PathVariable long companyId, @RequestBody Company company){
        if(companyRepository.exists(companyId)){
            companyRepository.updateCompany(companyId, company.getCompanyName(), company.getEmployeesNumber());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("cannot find such companyId", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/{companyId}")
    public ResponseEntity deleteCompany(@PathVariable long companyId){
        if(companyRepository.exists(companyId)){
            companyRepository.delete(companyId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("cannot find such companyId", HttpStatus.BAD_REQUEST);
    }
}


