package com.example.demospringboot.controller;

import com.example.demospringboot.entity.ProductEntity;
import com.example.demospringboot.model.common.ErrorResponse;
import com.example.demospringboot.model.common.SuccessResponse;
import com.example.demospringboot.model.common.SuccessResponseWithArray;
import com.example.demospringboot.model.product.RequestCreateProduct;
import com.example.demospringboot.model.product.RequestUpdateProduct;
import com.example.demospringboot.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private ProductService productService;

    public CustomerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/help-check")
    @ResponseBody
    public String helpCheck() {
        return "Customer Example Spring Boot";
    }

    @GetMapping("/product-list")
    public SuccessResponseWithArray getProductList() {
        List<ProductEntity> productList = this.productService.getProductList();
        return new SuccessResponseWithArray("200", "Success", (ArrayList) productList) ;
    }

    @GetMapping("/product-list/{id}")
    public SuccessResponseWithArray getProductListById(@PathVariable long id) {
        List<ProductEntity> productList = new ArrayList<>();
        productList = this.productService.getProductListById(id);
        return new SuccessResponseWithArray("200", "Success", (ArrayList) productList) ;    }

    @GetMapping("/product")
    public SuccessResponseWithArray getProductListByName(@RequestParam(defaultValue = "") String name){
        List<ProductEntity> productList = new ArrayList<>();
        productList = this.productService.getProductListByName(name);
        return new SuccessResponseWithArray("200", "Success", (ArrayList) productList);
    }

    @PostMapping("/product")
    public SuccessResponse createProduct(@RequestBody RequestCreateProduct requestCreateProduct) {
        try {
            this.productService.createProduct(requestCreateProduct);
            return new SuccessResponse("0000", "Success");
        }
        catch (Error error){
            throw new ErrorResponse(error.getMessage(), 1000, "Error");
        }

    }

    @PutMapping("/product")
    public SuccessResponse updateProduct(@RequestBody RequestUpdateProduct requestUpdateProduct) {
        try {
            this.productService.updateProduct(requestUpdateProduct);
            return  new SuccessResponse("0000", "Success");
        }
        catch (Exception e) {
            throw new ErrorResponse(e.getMessage(), 1000, "Error");
        }
    }
}
