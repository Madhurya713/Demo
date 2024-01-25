package com.example.demo.repo;

import com.example.demo.entity.EmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeDetailsDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsDao.class);
    private static final String HASH_KEY = "EmployeeDetails";

    @Autowired
    private RedisTemplate<String, Object> template;

    public EmployeeDetails save(EmployeeDetails employeeDetails) {
        logger.info("Saving EmployeeDetails with ID {}", employeeDetails.getId());
        template.opsForHash().put(HASH_KEY, employeeDetails.getId(), employeeDetails);
        return employeeDetails;
    }

    public Optional<EmployeeDetails> findById(Long id) {
        logger.info("Fetching EmployeeDetails with ID {}", id);
        EmployeeDetails employeeDetails = (EmployeeDetails) template.opsForHash().get(HASH_KEY, id);
        return Optional.ofNullable(employeeDetails);
    }

    public String deleteById(Long id) {
        logger.info("Deleting EmployeeDetails with ID {}", id);
        template.opsForHash().delete(HASH_KEY, id);
        return "EmployeeDetails removed with ID " + id;
    }

    public EmployeeDetails update(EmployeeDetails employeeDetails) {
        logger.info("Updating EmployeeDetails with ID {}", employeeDetails.getId());
        template.opsForHash().put(HASH_KEY, employeeDetails.getId(), employeeDetails);
        return employeeDetails;
    }
}
