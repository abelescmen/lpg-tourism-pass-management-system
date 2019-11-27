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
public class Vendor {

	@ApiModelProperty(value = "Vendor ID", example = "196d1b82-624d-476f-980b-8b38414a350e", required = true)
	@JsonPropertyDescription("Vendor ID")
	@JsonSerialize(using = ToStringSerializer.class)
	@NotNull
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();

	@ApiModelProperty(value = "Vendor Name", example = "Duck Tours", position = 1, required = true)
	@JsonPropertyDescription("Vendor Name")
	private String name;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Builder.Default
	private LocalDateTime lastUpdateDate = LocalDateTime.now();

}
