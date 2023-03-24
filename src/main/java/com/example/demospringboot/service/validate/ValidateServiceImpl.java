package com.example.demospringboot.service.validate;

import com.example.demospringboot.model.customer.RequestCreateCustomer;
import com.example.demospringboot.model.customer.RequestUpdateCustomer;
import com.example.demospringboot.util.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidateServiceImpl implements ValidateService{
    @Override
    public void validateRequestUpdateCustomer(RequestUpdateCustomer requestUpdateCustomer) throws UserException {
        this.validateFirstName(requestUpdateCustomer.getFirstName());
        this.validateLastName(requestUpdateCustomer.getLastName());
        this.validateEmail(requestUpdateCustomer.getEmail());
        this.validateTel(requestUpdateCustomer.getTel());
        this.validateId(requestUpdateCustomer.getId());
        this.validateStatusId(requestUpdateCustomer.getStatusId());
    }

    @Override
    public void validateRequestCreateCustomer(RequestCreateCustomer requestCreateCustomer) throws UserException {
        this.validateFirstName(requestCreateCustomer.getFirstName());
        this.validateLastName(requestCreateCustomer.getLastName());
        this.validateEmail(requestCreateCustomer.getEmail());
        this.validateTel(requestCreateCustomer.getTel());
    }
    private void validateFirstName(String firstName) throws UserException {
        if (Objects.isNull(firstName)) {
            throw UserException.valueIsNull("FirstName");
        }
    }
    private void validateLastName(String lastName) throws UserException {
        if (Objects.isNull(lastName)) {
            throw UserException.valueIsNull("LastName");
        }
    }
    private void validateTel(String tel) throws UserException {
        if (Objects.isNull(tel)) {
            throw UserException.valueIsNull("Tel");
        }
    }
    private void validateEmail(String email) throws UserException {
        if (Objects.isNull(email)) {
            throw UserException.valueIsNull("Email");
        }
    }
    private void validateId(long id) throws UserException {
        if (Objects.isNull(id) || id == 0) {
            throw UserException.valueIsNull("Id");
        }
    }
    private void validateStatusId(int statusId) throws UserException {
        if (Objects.isNull(statusId) || statusId == 0) {
            throw UserException.valueIsNull("StatusId");
        }
    }
}
