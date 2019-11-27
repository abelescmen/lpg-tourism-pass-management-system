package com.lpg.codechallenge.tourismpassmanagement.entity;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerEntity {

	@Id
	@Type(type = "pg-uuid")
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "home_city", nullable = false)
	private String homeCity;

	@Column(name = "create_date", nullable = false)
	private Timestamp createDate;

	@Column(name = "last_update_date", nullable = false)
	private Timestamp lastUpdateDate;

}
