package com.example.demospringboot.service.customer;

import com.example.demospringboot.entity.CustomerEntity;
import com.example.demospringboot.model.customer.RequestCreateCustomer;
import com.example.demospringboot.model.customer.RequestUpdateCustomer;
import com.example.demospringboot.repository.CustomerRepository;
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

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerEntity> getCustomerByTel(String tel) {
        return null;
    }

    @Override
    public List<CustomerEntity> getCustomerByName(String name) {
        return null;
    }

    @Override
    public CustomerEntity getCustomerById(long id) throws UserException {
        List<CustomerEntity> entity = this.customerRepository.findCustomerEntityById(id);
        if (entity.stream().findFirst().isPresent()) {
            return entity.get(0);
        } else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    public List<CustomerEntity> getCustomerByEmail(String email) {
        return null;
    }

    @Override
    @Transactional
    public void createCustomer(RequestCreateCustomer requestCreateCustomer) throws UserException {
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
        List<CustomerEntity> customerEntityList = this.customerRepository.findCustomerEntityById(requestUpdateCustomer.getId());
        if (!customerEntityList.isEmpty()) {
            CustomerEntity customerEntity = this.modelMapper.map(requestUpdateCustomer, CustomerEntity.class);
            this.logger.info(customerEntity.toString());
            try {
                this.customerRepository.updateCustomerById(
                        requestUpdateCustomer.getId(), requestUpdateCustomer.getFirstName(), requestUpdateCustomer.getLastName(), requestUpdateCustomer.getEmail(), requestUpdateCustomer.getTel()
                );
            } catch (Exception e) {
                throw UserException.updateUserNotFound();
            }
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
            try {
                this.customerRepository.updateCustomerStatusIdById(customerEntity.getStatusId(), customerEntity.getId());
            } catch (Exception e) {
                throw UserException.updateUserNotFound();
            }
        } else {
            throw UserException.dataNotFound();
        }

    }
}
