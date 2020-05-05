package example.spring.cloud.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import example.spring.cloud.eureka.dto.ExampleProductDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/dc/exam")
public class ProductCtrl {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;
    
    
    // http://localhost:8181/dc/exam/prodArray
    @RequestMapping("/prodArray")
    public List<ExampleProductDto> getProductsArrays() {
    	ResponseEntity<ExampleProductDto[]> response =
    			  restTemplate.getForEntity("http://dc-product-user/example/product", ExampleProductDto[].class);
    	ExampleProductDto[] prodArray = response.getBody();
    	List<ExampleProductDto> prodList = Arrays.asList(prodArray);
    	return prodList;
    }
    
    
    // http://localhost:8181/dc/exam/prodWrap
    // MismatchedInputException: Cannot deserialize instance > not working
    @RequestMapping("/prodWrap")
    public List<ExampleProductDto> getProductsWrapperClass() {
    	ExampleProductList response
    		= restTemplate.getForObject("http://dc-product-user/example/product", ExampleProductList.class);
    	List<ExampleProductDto> prodList = response.getProdList();
    	return prodList;
    }
    
    
    // http://localhost:8181/dc/exam/prodPTR
    @RequestMapping("/prodPTR")
    public List<ExampleProductDto> getProductsPTR() {
    	ResponseEntity<List<ExampleProductDto>> response =
                restTemplate.exchange("http://dc-product-user/example/product",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<ExampleProductDto>>() {});
        List<ExampleProductDto> prodList = response.getBody();
        return prodList;
    }
    
    
    // http://localhost:8181/dc/exam/prodRT/1
    @RequestMapping("/prodRT/{pId}")
    public ExampleProductDto getProductRT(@PathVariable("pId") String prodId) {
    	ExampleProductDto prod = restTemplate.getForObject("http://dc-product-user/example/product/" + prodId, ExampleProductDto.class);
    	return prod;
    }


    // http://localhost:8181/dc/exam/prodWC/1
    // uri method does not accept service name like dc-product-user > find solution > not working
    @RequestMapping("/prodWC/{pId}")
    public ExampleProductDto getProductWC(@PathVariable("pId") String prodId) {
    	ExampleProductDto prod = webClientBuilder.build()
    			.get()
    			.uri("http://dc-product-user/example/product/"+ prodId)
    			.retrieve()
    			.bodyToMono(ExampleProductDto.class)
    			.block();
    	return prod;
    }
    
}

class ExampleProductList {
	
    private List<ExampleProductDto> prodList;
 
    public ExampleProductList() {
    	prodList = new ArrayList<>();
    }

	public List<ExampleProductDto> getProdList() {
		return prodList;
	}

	public void setProdList(List<ExampleProductDto> prodList) {
		this.prodList = prodList;
	}
	
}
