package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.repository.CategoryRepo;
import com.educative.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.educative.ecommerce.service.TwoPCService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;
    
    @Autowired
    TwoPCService twoPCservice;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
                 Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            try {
                boolean flag = twoPCservice.prepareProduct();
                if (flag) {
                    twoPCservice.commitAddProduct(productDto, optionalCategory.get());
                    return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"),
                            HttpStatus.CREATED);
                }
            } catch (Exception e) {
                System.out.println("Something is going Wrong");
            }
        }
        twoPCservice.abortProduct();
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(false, "category does not exists or product has not been added"),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // create an api to edit the product
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("productId") Integer productId) throws Exception {

        Product product = productService.findById(productId);
        if (product != null) {
            try {
                boolean flag = twoPCservice.prepareProduct();
                if (flag) {
                    twoPCservice.commitDeleteProduct(productId);
                    return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been deleted"),
                            HttpStatus.CREATED);
                }
            } catch (Exception e) {
                System.out.println("Something is going Wrong");
            }
        }
        twoPCservice.abortProduct();
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "product does not exists"),
                HttpStatus.BAD_REQUEST);
    }
}
