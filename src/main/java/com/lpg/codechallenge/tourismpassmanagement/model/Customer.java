package com.lpg.codechallenge.tourismpassmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {

	@ApiModelProperty(value = "Customer ID", example = "2238e395-8a7a-44c6-aebd-7ae6cdd700cd", required = true)
	@JsonPropertyDescription("Customer ID")
	@JsonSerialize(using = ToStringSerializer.class)
	@NotNull
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();

	@ApiModelProperty(value = "Customer First Name", example = "John", position = 1, required = true)
	@JsonPropertyDescription("Customer First Name")
	private String firstName;

	@ApiModelProperty(value = "Customer Last Name", example = "Smith", position = 2, required = true)
	@JsonPropertyDescription("Customer Last Name")
	private String lastName;

	@ApiModelProperty(value = "Customer Home City", example = "Smith", position = 3, required = true)
	@JsonPropertyDescription("Customer Home City")
	private String homeCity;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Builder.Default
	private LocalDateTime lastUpdateDate = LocalDateTime.now();

}
