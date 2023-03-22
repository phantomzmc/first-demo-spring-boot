package com.example.demospringboot.service;

import com.example.demospringboot.entity.ProductEntity;
import com.example.demospringboot.model.product.RequestCreateProduct;
import com.example.demospringboot.model.product.RequestUpdateProduct;
import com.example.demospringboot.util.exception.BaseException;
import com.example.demospringboot.util.exception.UserException;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductEntity> getProductList();
    List<ProductEntity> getProductListByName(String name) throws UserException;
    ProductEntity getProductListById(long id) throws UserException;
    void updateProduct(RequestUpdateProduct requestUpdateProduct);
    void createProduct(RequestCreateProduct createProduct);
}
