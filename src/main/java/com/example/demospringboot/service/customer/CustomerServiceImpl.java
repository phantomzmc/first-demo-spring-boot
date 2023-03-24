package com.example.demospringboot.service.customer;

import com.example.demospringboot.entity.CustomerEntity;
import com.example.demospringboot.model.customer.RequestCreateCustomer;
import com.example.demospringboot.model.customer.RequestUpdateCustomer;
import com.example.demospringboot.repository.CustomerRepository;
import com.example.demospringboot.service.validate.ValidateService;
import com.example.demospringboot.util.exception.UserException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final CustomerRepository customerRepository;

    private final ValidateService validateService;

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository, ValidateService validateService) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.validateService = validateService;
    }

    @Override
    public List<CustomerEntity> getCustomerByTel(String tel) throws UserException {
        List<CustomerEntity> entity = this.customerRepository.findCustomerEntityByTel(tel);
        if (entity.stream().count() > 0) {
            return entity;
        } else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    public List<CustomerEntity> getCustomerByName(String name) throws UserException {
        List<CustomerEntity> entity = this.customerRepository.findCustomerEntityByFirstName(name);
        if (entity.stream().count() > 0) {
            return entity;
        } else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    public CustomerEntity getCustomerById(long id) throws UserException {
//        List<CustomerEntity> entity = this.customerRepository.findCustomerEntityByIdAndStatusId(id, 1);
        List<CustomerEntity> entity = this.customerRepository.findCustomerEntityById(id);
        if (entity.stream().findFirst().isPresent()) {
            if (entity.get(0).getStatusId() == 0){
                throw UserException.dataNotFound();
            } else {
                return entity.get(0);
            }
        } else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    public List<CustomerEntity> getCustomerByEmail(String email) throws UserException {
        List<CustomerEntity> entity = this.customerRepository.findCustomerEntityByEmail(email);
        if (entity.stream().count() > 0) {
            return entity;
        } else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    @Transactional
    public void createCustomer(RequestCreateCustomer requestCreateCustomer) throws UserException {
        this.validateService.validateRequestCreateCustomer(requestCreateCustomer);
        try {

            CustomerEntity customerEntity = this.modelMapper.map(requestCreateCustomer, CustomerEntity.class);
            this.logger.info(customerEntity.toString());
            this.customerRepository.save(customerEntity);
        } catch (Exception e) {
            throw UserException.createUserNotFound();
        }
    }

    @Override
    @Transactional
    public void updateCustomerById(RequestUpdateCustomer requestUpdateCustomer) throws UserException {
        this.validateService.validateRequestUpdateCustomer(requestUpdateCustomer);
        List<CustomerEntity> customerEntityList = this.customerRepository.findCustomerEntityById(requestUpdateCustomer.getId());
        if (!customerEntityList.isEmpty()) {
            CustomerEntity customerEntity = this.modelMapper.map(requestUpdateCustomer, CustomerEntity.class);
            this.logger.info(customerEntity.toString());
            this.updateCustomer(customerEntity);
        } else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    @Transactional
    public void updateCustomerStatusById(RequestUpdateCustomer requestUpdateCustomer) throws UserException {
        List<CustomerEntity> customerEntityList = this.customerRepository.findCustomerEntityById(requestUpdateCustomer.getId());
        if (!customerEntityList.isEmpty()) {
            CustomerEntity customerEntity = customerEntityList.get(0);
            customerEntity.setStatusId(requestUpdateCustomer.getStatusId());
            logger.info(customerEntity.toString());
            this.updateStatusCustomer(customerEntity);
        } else {
            throw UserException.dataNotFound();
        }

    }

    private void updateCustomer(CustomerEntity customerEntity) throws UserException {
        try {
            this.customerRepository.updateCustomerById(
                    customerEntity.getId(),
                    customerEntity.getFirstName(),
                    customerEntity.getLastName(),
                    customerEntity.getEmail(),
                    customerEntity.getTel()
            );
        } catch (Exception e) {
            throw UserException.updateUserNotFound();
        }
    }
    private void updateStatusCustomer(CustomerEntity customerEntity) throws UserException {
        try {
            this.customerRepository.updateCustomerStatusIdById(customerEntity.getStatusId(), customerEntity.getId());
        } catch (Exception e) {
            throw UserException.updateUserNotFound();
        }
    }
}
