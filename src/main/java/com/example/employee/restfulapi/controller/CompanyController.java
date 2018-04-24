package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    //在此处完成Company API
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public List<Company> GetAllCompanies(){
        return companyRepository.findAll();
    }

    @RequestMapping(value = "/companies/{companyId}", method = RequestMethod.GET)
    public Company GetCompanyById(@PathVariable long companyId){
        return companyRepository.findById(companyId);
    }

    @RequestMapping(value = "/companies/{companyId}/employees", method = RequestMethod.GET)
    public List<Employee> GetEmployeesByCompanyId(@PathVariable long companyId){
        return companyRepository.findEmployeesByCompanyId(companyId);
    }

    @RequestMapping(value = "/companies/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Company> GetCompaniesByPage(@PathVariable int page, @PathVariable int pageSize){
        return companyRepository.findAll(new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    public Company CreateNewCompany(@RequestBody Company company){
        Company flush = companyRepository.save(company);
        return flush;
    }

    @RequestMapping(value = "/companies/{companyId}", method = RequestMethod.PUT)
    public int updateCompany(@PathVariable long companyId, @RequestBody Company company){
        return companyRepository.updateCompany(companyId, company.getCompanyName(), company.getEmployeesNumber());
    }

    @RequestMapping(value="/companies/{companyId}", method = RequestMethod.DELETE)
    public void deleteCompany(@PathVariable long companyId){
        companyRepository.delete(companyId);
    }
}


