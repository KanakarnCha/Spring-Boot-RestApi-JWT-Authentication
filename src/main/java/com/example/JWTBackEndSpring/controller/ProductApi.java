package com.example.JWTBackEndSpring.controller;

import com.example.JWTBackEndSpring.entity.ProductEntity;
import com.example.JWTBackEndSpring.repository.ProductRepository;
import com.example.JWTBackEndSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductApi {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/findallProduct")
    public ResponseEntity<List<ProductEntity>> productAll(){
        return new ResponseEntity<>(productService.fndAllProduct(), HttpStatus.OK);
    }
    @GetMapping("/addProduct")
    public ResponseEntity<String> addProduct(){
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductName("Computer");
        productEntity1.setProductContent("Computer iS a Penmcil Laomm");
        productEntity1.setProductImage("https://drive.google.com/uc?export=view&id=1DB_hM3JJ2hZ-GlD2FfW_JOhXSprG4wVz");

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setProductName("Computer");
        productEntity2.setProductContent("Computer iS a Penmcil Laomm");
        productEntity2.setProductImage("https://drive.google.com/uc?export=view&id=1DB_hM3JJ2hZ-GlD2FfW_JOhXSprG4wVz");

        ProductEntity productEntity3 = new ProductEntity();
        productEntity3.setProductName("Computer");
        productEntity3.setProductContent("Computer iS a Penmcil Laomm");
        productEntity3.setProductImage("https://drive.google.com/uc?export=view&id=1DB_hM3JJ2hZ-GlD2FfW_JOhXSprG4wVz");
        productRepository.saveAll(List.of(productEntity1,productEntity2,productEntity3));
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
