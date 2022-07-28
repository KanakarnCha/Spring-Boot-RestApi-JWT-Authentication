package com.example.JWTBackEndSpring.service;

import com.example.JWTBackEndSpring.entity.ProductEntity;
import com.example.JWTBackEndSpring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<ProductEntity> fndAllProduct(){
        return productRepository.findAll();
    }
}
