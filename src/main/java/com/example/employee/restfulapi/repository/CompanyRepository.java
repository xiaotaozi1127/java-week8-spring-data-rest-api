package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Override
    List<Company> findAll();

    Company findById(long companyId);

    @Query("select a from Employee a, Company b where a.companyId = b.id and b.id = ?1")
    List<Employee> findEmployeesByCompanyId(long companyId);

    @Override
    Page<Company> findAll(Pageable request);

    @Override
    Company save(Company entity);
}
