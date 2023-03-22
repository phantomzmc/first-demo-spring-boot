package com.example.demospringboot.controller;

import com.example.demospringboot.entity.ProductEntity;
import com.example.demospringboot.model.common.SuccessResponse;
import com.example.demospringboot.model.common.SuccessResponseWithArray;
import com.example.demospringboot.model.common.SuccessResponseWithObject;
import com.example.demospringboot.model.product.RequestCreateProduct;
import com.example.demospringboot.model.product.RequestUpdateProduct;
import com.example.demospringboot.service.ProductService;
import com.example.demospringboot.util.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final ProductService productService;

    public CustomerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/help-check")
    @ResponseBody
    public String helpCheck() {
        return "Customer Example Spring Boot";
    }

    @GetMapping("/product-list")
    public ResponseEntity<SuccessResponseWithArray> getProductList() {
        List<ProductEntity> productList = this.productService.getProductList();
        return ResponseEntity.ok(new SuccessResponseWithArray("200", "Success", (ArrayList) productList)) ;
    }

    @GetMapping("/product-list/{id}")
    public ResponseEntity<SuccessResponseWithObject>  getProductListById(@PathVariable long id) throws BaseException {
        ProductEntity product = new ProductEntity();
            product = this.productService.getProductListById(id);
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
            this.productService.createProduct(requestCreateProduct);
            return ResponseEntity.ok(new SuccessResponse("0000", "Success"));
    }

    @PutMapping("/product")
    public ResponseEntity<SuccessResponse> updateProduct(@RequestBody RequestUpdateProduct requestUpdateProduct) {
            this.productService.updateProduct(requestUpdateProduct);
            return ResponseEntity.ok(new SuccessResponse("0000", "Success"));
    }
}
