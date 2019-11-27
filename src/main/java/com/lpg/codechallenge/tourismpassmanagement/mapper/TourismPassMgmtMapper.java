package com.lpg.codechallenge.tourismpassmanagement.mapper;

import com.lpg.codechallenge.tourismpassmanagement.entity.CustomerEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.PassEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.VendorEntity;
import com.lpg.codechallenge.tourismpassmanagement.model.Customer;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import com.lpg.codechallenge.tourismpassmanagement.model.Vendor;
import java.sql.Date;
import java.sql.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
	nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
	unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TourismPassMgmtMapper {

	/**
	 * Mapping Models to Entities
	 */

	default CustomerEntity toCustomerEntity(final Customer customer) {
		if (customer == null) {
			return null;
		}
		return CustomerEntity.builder()
			.id(customer.getId())
			.firstName(customer.getFirstName())
			.lastName(customer.getLastName())
			.homeCity(customer.getHomeCity())
			.createDate(customer.getCreateDate() != null ? Timestamp.valueOf(customer.getCreateDate()) : null)
			.lastUpdateDate(customer.getLastUpdateDate() != null ? Timestamp.valueOf(customer.getLastUpdateDate()) : null)
			.build();
	}

	default VendorEntity toVendorEntity(final Vendor vendor) {
		if (vendor == null) {
			return null;
		}
		return VendorEntity.builder()
			.id(vendor.getId())
			.name(vendor.getName())
			.createDate(vendor.getCreateDate() != null ? Timestamp.valueOf(vendor.getCreateDate()) : null)
			.lastUpdateDate(vendor.getLastUpdateDate() != null ? Timestamp.valueOf(vendor.getLastUpdateDate()) : null)
			.build();
	}

	default PassEntity toPassEntity(final Pass pass) {
		if (pass == null) {
			return null;
		}
		return PassEntity.builder()
			.id(pass.getId())
			.city(pass.getCity())
			.length(pass.getLength())
			.startDate(pass.getStartDate() != null ? Date.valueOf(pass.getStartDate()) : null)
			.expirationDate(pass.getExpirationDate() != null ? Date.valueOf(pass.getExpirationDate()) : null)
			.cancelIndicator(pass.isCancelIndicator())
			.createDate(pass.getCreateDate() != null ? Timestamp.valueOf(pass.getCreateDate()) : null)
			.lastUpdateDate(pass.getLastUpdateDate() != null ? Timestamp.valueOf(pass.getLastUpdateDate()) : null)
			.customer(toCustomerEntity(pass.getCustomer()))
			.vendor(toVendorEntity(pass.getVendor()))
			.build();
	}



	/**
	 * Mapping Entities to Models
	 */

	default Customer toCustomer(final CustomerEntity customerEntity) {
		if (customerEntity == null) {
			return null;
		}
		return Customer.builder()
			.id(customerEntity.getId())
			.firstName(customerEntity.getFirstName())
			.lastName(customerEntity.getLastName())
			.homeCity(customerEntity.getHomeCity())
			.createDate(customerEntity.getCreateDate() != null ? customerEntity.getCreateDate().toLocalDateTime() : null)
			.lastUpdateDate(customerEntity.getLastUpdateDate() != null ? customerEntity.getLastUpdateDate().toLocalDateTime() : null)
			.build();
	}

	default Vendor toVendor(final VendorEntity vendorEntity) {
		if (vendorEntity == null) {
			return null;
		}
		return Vendor.builder()
			.id(vendorEntity.getId())
			.name(vendorEntity.getName())
			.createDate(vendorEntity.getCreateDate() != null ? vendorEntity.getCreateDate().toLocalDateTime() : null)
			.lastUpdateDate(vendorEntity.getLastUpdateDate() != null ? vendorEntity.getLastUpdateDate().toLocalDateTime() : null)
			.build();
	}

	default Pass toPass(final PassEntity passEntity) {
		if (passEntity == null) {
			return null;
		}
		return Pass.builder()
			.id(passEntity.getId())
			.city(passEntity.getCity())
			.length(passEntity.getLength())
			.startDate(passEntity.getStartDate() != null ? passEntity.getStartDate().toLocalDate() : null)
			.expirationDate(passEntity.getExpirationDate() != null ? passEntity.getExpirationDate().toLocalDate() : null)
			.cancelIndicator(passEntity.isCancelIndicator())
			.createDate(passEntity.getCreateDate() != null ? passEntity.getCreateDate().toLocalDateTime() : null)
			.lastUpdateDate(passEntity.getLastUpdateDate() != null ? passEntity.getLastUpdateDate().toLocalDateTime() : null)
			.customer(toCustomer(passEntity.getCustomer()))
			.vendor(toVendor(passEntity.getVendor()))
			.build();
	}

}
