package example.spring.cloud.eureka.service;

import java.util.List;

import example.spring.cloud.eureka.dto.ExampleProductDto;

public interface ProductService2 {
	
	public List<ExampleProductDto> getProductsWrapperClass();
	
	public List<ExampleProductDto> getProductsPTR();
	
	public ExampleProductDto getProductRT(int prodId);
	
	public ExampleProductDto getProductWC(int prodId);

}
