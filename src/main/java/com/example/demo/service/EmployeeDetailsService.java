package com.example.demo.service;

import com.example.demo.entity.EmployeeDetails;
import com.example.demo.repo.EmployeeDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsDao employeeDetailsDao;

    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        return employeeDetailsDao.save(employeeDetails);
    }

    public Optional<EmployeeDetails> getEmployeeDetails(Long id) {
        return employeeDetailsDao.findById(id);
    }

    public String deleteEmployeeDetails(Long id) {
        return employeeDetailsDao.deleteById(id);
    }

    public EmployeeDetails updateEmployeeDetails(EmployeeDetails employeeDetails) {
        return employeeDetailsDao.update(employeeDetails);
    }

    // Additional business logic methods can be added here
}
