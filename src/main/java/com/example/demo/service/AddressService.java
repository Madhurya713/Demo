package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.repo.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public Address saveAddress(Address address) {
        return addressDao.save(address);
    }

    public Optional<Address> getAddress(Long id) {
        return addressDao.findById(id);
    }

    public String deleteAddress(Long id) {
        return addressDao.deleteById(id);
    }

    public Address updateAddress(Address address) {
        return addressDao.update(address);
    }

    // Additional business logic methods can be added here
}
