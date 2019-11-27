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
@Table(name = "vendor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VendorEntity {

	@Id
	@Type(type = "pg-uuid")
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "create_date", nullable = false)
	private Timestamp createDate;

	@Column(name = "last_update_date", nullable = false)
	private Timestamp lastUpdateDate;

}
