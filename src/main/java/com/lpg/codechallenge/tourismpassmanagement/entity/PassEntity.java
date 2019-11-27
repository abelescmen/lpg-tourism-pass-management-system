package com.lpg.codechallenge.tourismpassmanagement.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "pass")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PassEntity {

	@Id
	@Type(type = "pg-uuid")
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "length", nullable = false)
	private Integer length;

	@Column(name = "cancel_indicator", nullable = false)
	private boolean cancelIndicator;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;

	@Column(name = "create_date", nullable = false)
	private Timestamp createDate;

	@Column(name = "last_update_date", nullable = false)
	private Timestamp lastUpdateDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id", nullable = false)
	private VendorEntity vendor;

}
