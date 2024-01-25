package com.example.demo.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {
        long time = System.currentTimeMillis();
        int randomNumber = new Random().nextInt(9999);
        return time + String.format("%04d", randomNumber);
    }
}
