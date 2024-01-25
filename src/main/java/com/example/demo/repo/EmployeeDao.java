package com.example.demo.repo;

import com.example.demo.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EmployeeDao {
//    private static final String HASH_KEY = "Employee";
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

    @Autowired
    private RedisTemplate<String, Object> template;

    /**
     * Saves an employee to Redis.
     *
     * @param employee The employee to save.
     * @param hashKey  The hash key under which to save the employee.
     * @return The saved employee.
     */
    public Employee save(Employee employee, String hashKey) {
        logger.info("Saving employee with ID {}", employee.getId());
        template.opsForHash().put(hashKey, employee.getId(), employee);
        return employee;
    }

    /**
     * Finds all employees stored in Redis.
     *
     * @param hashKey The hash key under which the employees are stored.
     * @return A list of all employees.
     */
    public List<Employee> findAll(String hashKey) {
        logger.info("Fetching all employees");
        List<Object> objects = template.opsForHash().values(hashKey);
        List<Employee> employees = new ArrayList<>();
        for (Object o : objects) {
            if (o instanceof Employee) {
                employees.add((Employee) o);
            } else {
                logger.error("Object in Redis is not an instance of Employee: {}", o);
            }
        }
        return employees;
    }

    /**
     * Finds an employee by ID.
     *
     * @param id      The ID of the employee to find.
     * @param hashKey The hash key under which the employee is stored.
     * @return An Optional containing the found employee or empty if not found.
     */
    public Optional<Employee> findById(Long id, String hashKey) {
        logger.info("Fetching employee with ID {}", id);
        Employee employee = (Employee) template.opsForHash().get(hashKey, id);
        return Optional.ofNullable(employee);
    }

    /**
     * Deletes an employee by ID from Redis.
     *
     * @param id      The ID of the employee to delete.
     * @param hashKey The hash key under which the employee is stored.
     * @return A message indicating successful deletion.
     */
    public String deleteById(long id, String hashKey) {
        logger.info("Deleting employee with ID {}", id);
        template.opsForHash().delete(hashKey, id);
        return "Employee removed with ID " + id;
    }

    /**
     * Checks if an employee exists by ID in Redis.
     *
     * @param id      The ID of the employee to check.
     * @param hashKey The hash key under which the employee might be stored.
     * @return True if the employee exists, false otherwise.
     */
    public boolean existsById(Long id, String hashKey) {
        logger.info("Checking if employee exists with ID {}", id);
        return template.opsForHash().hasKey(hashKey, id);
    }

    public List<Employee> findByName(String name) {
        logger.info("Searching for employees with name {}", name);
        List<Employee> allEmployees = findAll("Employee");
        return allEmployees.stream()
                .filter(employee -> name.equals(employee.getName()))
                .collect(Collectors.toList());
    }
}