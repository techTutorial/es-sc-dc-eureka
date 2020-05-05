package example.spring.cloud.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.spring.cloud.eureka.dto.ExampleProductDto;
import example.spring.cloud.eureka.service.ProductService;
import example.spring.cloud.eureka.service.ProductService2;

import java.util.List;

@RestController
@RequestMapping("/dc/exam")
public class ProductCtrl {
    
    @Autowired
    private ProductService prodService;
    
    @Autowired
    private ProductService2 prodService2;
    
    
    // http://localhost:8181/dc/exam/prodArray
    @RequestMapping("/prodArray")
    public List<ExampleProductDto> getProductsArrays() {
    	return prodService.getProductsArrays();
    }
    
    
    // http://localhost:8181/dc/exam/prodWrap
    // MismatchedInputException: Cannot deserialize instance > not working
    @RequestMapping("/prodWrap")
    public List<ExampleProductDto> getProductsWrapperClass() {
    	return prodService2.getProductsWrapperClass();
    }
    
    
    // http://localhost:8181/dc/exam/prodPTR
    @RequestMapping("/prodPTR")
    public List<ExampleProductDto> getProductsPTR() {
    	return prodService2.getProductsPTR();
    }
    
    
    // http://localhost:8181/dc/exam/prodRT/1
    @RequestMapping("/prodRT/{pId}")
    public ExampleProductDto getProductRT(@PathVariable("pId") int prodId) {
    	return prodService2.getProductRT(prodId);
    }


    // http://localhost:8181/dc/exam/prodWC/1
    // uri method does not accept service name like dc-product-user > find solution > not working
    @RequestMapping("/prodWC/{pId}")
    public ExampleProductDto getProductWC(@PathVariable("pId") int prodId) {
    	return prodService2.getProductWC(prodId);
    }
    
}
