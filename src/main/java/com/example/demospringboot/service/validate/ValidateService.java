package com.example.demospringboot.service.validate;

import com.example.demospringboot.model.customer.RequestCreateCustomer;
import com.example.demospringboot.model.customer.RequestUpdateCustomer;
import com.example.demospringboot.util.exception.UserException;

public interface ValidateService {

    void validateRequestUpdateCustomer(RequestUpdateCustomer requestUpdateCustomer) throws UserException;
    void validateRequestCreateCustomer(RequestCreateCustomer requestCreateCustomer) throws UserException;
}
