package example.spring.cloud.eureka.dto;

import java.util.Optional;

public class ExampleAccessoryDto {
	
	private Integer accessoryId;
	private String accessoryName;
	
	public ExampleAccessoryDto(int accessoryId, String accessoryName) {
		this.accessoryId = accessoryId;
		this.accessoryName = accessoryName;
	}
	public Integer getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Integer accessoryId) {
		this.accessoryId = accessoryId;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public Optional<String> getAccessoryNameOpt() {
		return Optional.ofNullable(accessoryName);
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

}
