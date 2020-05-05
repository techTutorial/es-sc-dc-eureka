package example.spring.cloud.eureka.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ExampleProductDto implements Serializable, Comparable<ExampleProductDto> {
	
	private static final long serialVersionUID = 1L;
	
	public enum Availability {
		IN_STOCK, SOLD_OUT;
	}
	
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Availability availability;
	
	private ExampleAccessoryDto exampleAccessoryDto;
	private List<ExampleAccessoryDto> exampleAccessoryDtoList;

	// remove both the constructors
	public ExampleProductDto(){
	}
	
	public ExampleProductDto(Integer productPrice, String productName){
		this.setProductPrice(productPrice);
		this.setProductName(productName);
	}
	
	public ExampleProductDto(Integer productId, String productName, Integer productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	public ExampleProductDto(Integer productId, String productName, Integer productPrice, Availability availability) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.availability = availability;
	}
	
	public ExampleProductDto(Integer productId, String productName, Integer productPrice, ExampleAccessoryDto exampleAccessoryDto) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.exampleAccessoryDto = exampleAccessoryDto;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Availability getAvailability() {
		return availability;
	}
	
	public ExampleAccessoryDto getExampleAccessoryDto() {
		return exampleAccessoryDto;
	}
	
	public Optional<ExampleAccessoryDto> getExampleAccessoryDtoOpt() {
		return Optional.ofNullable(exampleAccessoryDto);
	}
	
	public List<ExampleAccessoryDto> getExampleAccessoryDtoList() {
		return exampleAccessoryDtoList;
	}

	public void setExampleAccessoryDtoList(List<ExampleAccessoryDto> exampleAccessoryDtoList) {
		this.exampleAccessoryDtoList = exampleAccessoryDtoList;
	}
	
	@Override
	public int compareTo(ExampleProductDto dto) {
		return productId.compareTo(dto.getProductId());
	}
	
	@Override
	public String toString() {
		return String.format("Product[%s, %s, %d, %s]\n", productId, productName, productPrice, availability);
	}
	
	//hashCode formula- length of instance name
	@Override
	public int hashCode() {
		return this.getProductName().length();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof ExampleProductDto) && (((ExampleProductDto) obj).getProductName() == this.productName)) {
			return true;
		} else {
			return false;
		}
	}

}
