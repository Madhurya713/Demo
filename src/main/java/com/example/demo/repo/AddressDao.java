package com.example.demo.repo;

import com.example.demo.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressDao {

    private static final Logger logger = LoggerFactory.getLogger(AddressDao.class);
    private static final String HASH_KEY = "Address";

    @Autowired
    private RedisTemplate<String, Object> template;

    public Address save(Address address) {
        logger.info("Saving Address with ID {}", address.getId());
        template.opsForHash().put(HASH_KEY, address.getId(), address);
        return address;
    }

    public Optional<Address> findById(Long id) {
        logger.info("Fetching Address with ID {}", id);
        Address address = (Address) template.opsForHash().get(HASH_KEY, id);
        return Optional.ofNullable(address);
    }

    public String deleteById(Long id) {
        logger.info("Deleting Address with ID {}", id);
        template.opsForHash().delete(HASH_KEY, id);
        return "Address removed with ID " + id;
    }

    public Address update(Address address) {
        logger.info("Updating Address with ID {}", address.getId());
        template.opsForHash().put(HASH_KEY, address.getId(), address);
        return address;
    }
}
