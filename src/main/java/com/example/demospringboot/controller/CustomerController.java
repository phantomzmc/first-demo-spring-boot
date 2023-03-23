package com.example.demospringboot.controller;

import com.example.demospringboot.entity.CustomerEntity;
import com.example.demospringboot.entity.ProductEntity;
import com.example.demospringboot.model.common.SuccessResponse;
import com.example.demospringboot.model.common.SuccessResponseWithArray;
import com.example.demospringboot.model.common.SuccessResponseWithObject;
import com.example.demospringboot.model.customer.RequestCreateCustomer;
import com.example.demospringboot.model.customer.RequestUpdateCustomer;
import com.example.demospringboot.model.product.RequestCreateProduct;
import com.example.demospringboot.model.product.RequestUpdateProduct;
import com.example.demospringboot.service.customer.CustomerService;
import com.example.demospringboot.service.product.ProductService;
import com.example.demospringboot.util.exception.BaseException;
import com.example.demospringboot.util.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ProductService productService;
    private final CustomerService customerService;

    public CustomerController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/help-check")
    @ResponseBody
    public String helpCheck() {
        return "Customer Example Spring Boot";
    }

    @GetMapping("/product-list")
    public ResponseEntity<SuccessResponseWithArray> getProductList() {
        List<ProductEntity> productList = this.productService.getProductList();
        logger.info(productList.toString());
        return ResponseEntity.ok(new SuccessResponseWithArray("200", "Success", (ArrayList) productList)) ;
    }

    @GetMapping("/product-list/{id}")
    public ResponseEntity<SuccessResponseWithObject>  getProductListById(@PathVariable long id) throws BaseException {
        ProductEntity product = new ProductEntity();
        product = this.productService.getProductListById(id);
        logger.info(product.toString());
        return ResponseEntity.ok(new SuccessResponseWithObject("200", "Success", product));

    }

    @GetMapping("/product")
    public ResponseEntity<SuccessResponseWithArray> getProductListByName(@RequestParam(defaultValue = "") String name) throws BaseException{
        List<ProductEntity> productList = new ArrayList<>();
        productList = this.productService.getProductListByName(name);
        return ResponseEntity.ok(new SuccessResponseWithArray("200", "Success", (ArrayList) productList));
    }

    @PostMapping("/product")
    public ResponseEntity<SuccessResponse> createProduct(@RequestBody RequestCreateProduct requestCreateProduct) {
        logger.info(requestCreateProduct.toString());
        this.productService.createProduct(requestCreateProduct);
        return ResponseEntity.ok(new SuccessResponse("0000", "Success"));
    }

    @PutMapping("/product")
    public ResponseEntity<SuccessResponse> updateProduct(@RequestBody RequestUpdateProduct requestUpdateProduct) {
        logger.info(requestUpdateProduct.toString());
        this.productService.updateProduct(requestUpdateProduct);
        return ResponseEntity.ok(new SuccessResponse("0000", "Success"));
    }

    @GetMapping("/customers")
    public ResponseEntity<SuccessResponseWithObject> getCustomerById(@RequestParam(defaultValue = "0") long id) throws UserException {
        CustomerEntity customer = new CustomerEntity();
        customer = this.customerService.getCustomerById(id);
        return ResponseEntity.ok(new SuccessResponseWithObject("200", "Success", customer));
    }

    @PostMapping("/customers")
    public ResponseEntity<SuccessResponse> createCustomer(@RequestBody RequestCreateCustomer requestCreateCustomer) throws UserException {
        logger.info(requestCreateCustomer.toString());
        this.customerService.createCustomer(requestCreateCustomer);
        return ResponseEntity.ok(new SuccessResponse("200", "Success"));
    }
    @PutMapping("/customers")
    public ResponseEntity<SuccessResponse> updateCustomerById(@RequestBody RequestUpdateCustomer requestUpdateCustomer) throws UserException {
        logger.info(requestUpdateCustomer.toString());
        this.customerService.updateCustomerById(requestUpdateCustomer);
        return ResponseEntity.ok(new SuccessResponse("200", "Success"));
    }

    @PutMapping("/customers-id")
    public  ResponseEntity<SuccessResponse> updateCustomerStatusById(@RequestBody RequestUpdateCustomer requestUpdateCustomer) throws UserException {
        logger.info(requestUpdateCustomer.toString());
        this.customerService.updateCustomerStatusById(requestUpdateCustomer);
        return ResponseEntity.ok(new SuccessResponse("200", "Success"));
    }
}
