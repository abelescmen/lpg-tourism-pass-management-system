package com.lpg.codechallenge.tourismpassmanagement.model.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Contact;

@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {

	private String title;
	private String description;
	private String version;
	private String termsOfServiceUrl;
	private Contact contact;
	private String license;
	private String licenseUrl;

}
