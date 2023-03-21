package com.example.demospringboot.service;

import com.example.demospringboot.entity.ProductEntity;
import com.example.demospringboot.model.product.RequestCreateProduct;
import com.example.demospringboot.model.product.RequestUpdateProduct;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getProductList();
    List<ProductEntity> getProductListByName(String name);
    List<ProductEntity> getProductListById(long id);
    void updateProduct(RequestUpdateProduct requestUpdateProduct);
    void createProduct(RequestCreateProduct createProduct);
}
