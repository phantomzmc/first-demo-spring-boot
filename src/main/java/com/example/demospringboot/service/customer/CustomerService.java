package com.example.demospringboot.service.customer;

import com.example.demospringboot.entity.CustomerEntity;
import com.example.demospringboot.model.customer.RequestCreateCustomer;
import com.example.demospringboot.model.customer.RequestUpdateCustomer;
import com.example.demospringboot.util.exception.UserException;

import java.util.List;

public interface CustomerService {

    List<CustomerEntity> getCustomerByTel(String tel);
    List<CustomerEntity> getCustomerByName(String name);
    CustomerEntity getCustomerById(long id) throws UserException;
    List<CustomerEntity> getCustomerByEmail(String email);
    void createCustomer(RequestCreateCustomer requestCreateCustomer) throws UserException;
    void updateCustomerById(RequestUpdateCustomer requestUpdateCustomer) throws UserException;
    void updateCustomerStatusById(RequestUpdateCustomer requestUpdateCustomer) throws UserException;

}
