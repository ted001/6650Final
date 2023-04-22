package com.educative.ecommerce.service;

import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.exceptions.ProductNotExistsException;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ProductService {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) throws Exception {
        if (lock.writeLock().tryLock()) {
            // tryLock() to prevent deadlock
            try {
                Product product = new Product();
                product.setDescription(productDto.getDescription());
                product.setImageURL(productDto.getImageURL());
                product.setName(productDto.getName());
                product.setCategory(category);
                product.setPrice(productDto.getPrice());
                productRepository.save(product);
            } finally {
                lock.writeLock().unlock();
            }
        } else {
            throw new Exception("failed to acquire lock");
        }
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        if (lock.writeLock().tryLock()) {
            // tryLock() to prevent deadlock
            try {
                Optional<Product> optionalProduct = productRepository.findById(productId);
                // throw an exception if product does not exists
                if (!optionalProduct.isPresent()) {
                    throw new Exception("product not present");
                }
                Product product = optionalProduct.get();
                product.setDescription(productDto.getDescription());
                product.setImageURL(productDto.getImageURL());
                product.setName(productDto.getName());
                product.setPrice(productDto.getPrice());
                productRepository.save(product);
            } finally {
                lock.writeLock().unlock();
            }
        } else {
            throw new Exception("failed to acquire lock");
        }
    }

    public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
}
