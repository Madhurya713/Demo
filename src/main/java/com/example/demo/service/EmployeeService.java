package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeDao;
//import com.example.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
//    @Autowired
//    EmployeeRepository employeeRepository;

        private static final String HASH_KEY = "Employee";


    @Autowired
    EmployeeDao employeeDao;

    /**
     * Adds an employee to the repository and cache.
     *
     * @param employee The employee to be added.
     * @return The added employee.
     * @throws IllegalArgumentException if the employee is null.
     */
    @Cacheable(key = "#employee.id", value = "Employee")
    public Employee addEmployee(Employee employee) {
        if (employee == null) {

            throw new IllegalArgumentException("Employee must not be null");
        }
        try {
//            Employee emp = employeeRepository.saveAndFlush(employee);
            Employee emp = employeeDao.save(employee, HASH_KEY);
            return emp;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * Deletes an employee by ID and removes it from the cache.
     *
     * @param employeeId The ID of the employee to delete.
     */
    @CacheEvict(key = "#employee.id", value = "Employee")
    public void deleteEmployee(Long employeeId) {
        try {
//            employeeRepository.deleteById(employeeId);
            employeeDao.deleteById(employeeId, HASH_KEY);
            return;
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param employeeId The ID of the employee to find.
     * @return The found employee.
     * @throws IllegalArgumentException if the employee with the given ID is not found.
     */
    @Cacheable(value = "Employee", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        return employeeDao.findById(employeeId, HASH_KEY)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));
    }

    /**
     * Updates an existing employee and refreshes the cache.
     *
     * @param employee The employee with updated details.
     * @return The updated employee.
     * @throws IllegalArgumentException if the employee or employee ID is null, or if the employee doesn't exist.
     */
    @CachePut(value = "Employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        if (employee == null || employee.getId() == null) {
            throw new IllegalArgumentException("Employee or Employee ID must not be null");
        }
        if (!employeeDao.existsById(employee.getId(), HASH_KEY)) {
            throw new IllegalArgumentException("Employee not found with ID: " + employee.getId());
        }
        return employeeDao.save(employee, HASH_KEY);
    }

    /**
     * Retrieves all employees from the repository.
     *
     * @return A list of all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll(HASH_KEY);
    }
}
