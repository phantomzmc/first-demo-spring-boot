package com.example.demospringboot.service.product;

import com.example.demospringboot.entity.ProductEntity;
import com.example.demospringboot.model.product.RequestCreateProduct;
import com.example.demospringboot.model.product.RequestUpdateProduct;
import com.example.demospringboot.repository.ProductRepository;
import com.example.demospringboot.util.exception.UserException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void createProduct(RequestCreateProduct createProduct) {
        ProductEntity entity = new ProductEntity(createProduct.getName(), createProduct.getType(), createProduct.getPrice());
        logger.debug(entity.toString());
        this.productRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateProduct(RequestUpdateProduct requestUpdateProduct) {
        ProductEntity entity = new ProductEntity(requestUpdateProduct.getId(), requestUpdateProduct.getName(), requestUpdateProduct.getType(), requestUpdateProduct.getPrice());
        this.productRepository.updateProductById(entity.getId(), entity.getName(), entity.getType(), entity.getPrice());
    }

    @Override
    public ProductEntity getProductListById(long id) throws UserException {
        ProductEntity result = new ProductEntity();
        result = this.productRepository.findFirstById(id);
        if (result != null){
            logger.info(result.toString());
            return result;
        }
        else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    public List<ProductEntity> getProductListByName(String name) throws UserException {
        List<ProductEntity> result = new ArrayList<ProductEntity>();
        result = this.productRepository.findByNameContainingIgnoreCase(name);
        logger.debug(result.toString());
        if (!result.isEmpty()){
            return result;
        }
        else {
            throw UserException.dataNotFound();
        }
    }

    @Override
    public List<ProductEntity> getProductList() {
        return this.productRepository.findAll();
    }
}
