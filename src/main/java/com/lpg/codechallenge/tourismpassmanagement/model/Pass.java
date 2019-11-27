package com.lpg.codechallenge.tourismpassmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
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
public class Pass {

	@ApiModelProperty(value = "Pass ID", example = "02e357c7-68d8-4873-9829-b0d2d15eb79a")
	@JsonPropertyDescription("Pass ID")
	@JsonSerialize(using = ToStringSerializer.class)
	@NotNull
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();

	@ApiModelProperty(value = "Pass City", example = "Boston", position = 1, required = true)
	@JsonPropertyDescription("Pass City")
	@NotNull
	private String city;

	@ApiModelProperty(value = "Pass Duration", example = "30", position = 2, required = true)
	@JsonPropertyDescription("Pass Duration")
	@NotNull
	private Integer length;

	@ApiModelProperty(value = "Pass Start Date", example = "2019-12-01", position = 3, required = true)
	@JsonPropertyDescription("Pass Start Date")
	@NotNull
	private LocalDate startDate;

	@ApiModelProperty(value = "Pass Expiration Date", example = "2019-12-30", position = 4, required = true)
	@JsonPropertyDescription("Pass Expiration Date")
	private LocalDate expirationDate;

	@ApiModelProperty(value = "Pass Cancel Indicator", example = "false", position = 5)
	@JsonPropertyDescription("Pass Cancel Indicator")
	@Builder.Default
	private boolean cancelIndicator = false;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Builder.Default
	private LocalDateTime lastUpdateDate = LocalDateTime.now();

	@ApiModelProperty(value = "Vendor", position = 6, required = true)
	@JsonPropertyDescription("Customer Name")
//	@NotNull
	private Customer customer;

	@ApiModelProperty(value = "Customer", position = 7, required = true)
	@JsonPropertyDescription("Customer")
//	@NotNull
	private Vendor vendor;

}
