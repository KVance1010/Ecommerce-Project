package com.ecommerce.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.model.Address;
import com.ecommerce.model.Order;

public class OneToOneBidirectionalTest {

	@Autowired
    private AddressRepository addressRepository;

    @Test
    void saveAddressMethod(){

        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");

        order.setBillingAddress(address);
        address.setOrder(order);

        addressRepository.save(address);
    }

    @Test
    void updateAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        address.setZipCode("411048");

        address.getOrder().setStatus("DELIVERED");

        addressRepository.save(address);
    }

    @Test
    void fetchAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        System.out.println(address);
    }

    @Test
    void deleteAddressMethod(){
        addressRepository.deleteById(1L);
    }
}

