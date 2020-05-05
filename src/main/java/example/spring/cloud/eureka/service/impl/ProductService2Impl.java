package example.spring.cloud.eureka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import example.spring.cloud.eureka.dto.ExampleProductDto;
import example.spring.cloud.eureka.service.ProductService2;

@Service
public class ProductService2Impl implements ProductService2 {
	
	@Autowired
	@Qualifier("dcRt")
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String PROD_USER_API_URI = "http://dc-product-user";

	@Override
	public List<ExampleProductDto> getProductsWrapperClass() {
		ExampleProductList response
			= restTemplate.getForObject(PROD_USER_API_URI + "/example/product", ExampleProductList.class);
		List<ExampleProductDto> prodList = response.getProdList();
		return prodList;
	}

	@Override
	public List<ExampleProductDto> getProductsPTR() {
		ResponseEntity<List<ExampleProductDto>> response =
                restTemplate.exchange(PROD_USER_API_URI + "/example/product",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<ExampleProductDto>>() {});
        List<ExampleProductDto> prodList = response.getBody();
        return prodList;
	}

	@Override
	public ExampleProductDto getProductRT(int prodId) {
		ExampleProductDto prod = restTemplate.getForObject(PROD_USER_API_URI + "/example/product/" + prodId, ExampleProductDto.class);
    	return prod;
	}

	@Override
	public ExampleProductDto getProductWC(int prodId) {
		ExampleProductDto prod = webClientBuilder.build()
    			.get()
    			.uri(PROD_USER_API_URI + "/example/product/"+ prodId)
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
