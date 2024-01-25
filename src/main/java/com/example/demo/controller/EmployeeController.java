package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/employee")
public class EmployeeController {
    private static final String HASH_KEY = "Employee";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Creates a new employee.
     *
     * @param employee The employee to create.
     * @return The created employee with status 201 (Created), or status 400 (Bad Request) if input is null.
     */
    @PostMapping("/create")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        if (employee == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Employee addedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }


    /**
     * Updates an existing employee.
     *
     * @param employee The employee to update.
     * @return The updated employee, or status 400 (Bad Request) if input is invalid.
     */
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        if (employee == null || employee.getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The requested employee, or status 404 (Not Found) if not found, or status 400 (Bad Request) if ID is null.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            Employee employee = employeeService.getEmployee(id);
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Lists all employees.
     *
     * @return A list of all employees.
     */
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete.
     * @return Status 200 (OK) on successful deletion, or status 400 (Bad Request) if ID is null.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getToken")
    public String generateToken(String userName){
        return jwtUtil.generateToken(userName);
    }
}
