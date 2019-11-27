package com.lpg.codechallenge.tourismpassmanagement.repository;

import com.lpg.codechallenge.tourismpassmanagement.entity.CustomerEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.PassEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.VendorEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PassRepository extends JpaRepository<PassEntity, UUID> {

	Optional <PassEntity> findByIdAndVendor(final UUID passId, final VendorEntity vendor);

	List<PassEntity> findByCustomer(final CustomerEntity customer);

	List<PassEntity> findByVendor(final VendorEntity vendor);

	@Query(
		value = "SELECT * "
				+ "FROM pass "
				+ "WHERE cancel_indicator = :cancelIndicator "
				+ "AND city = :city "
				+ "AND (:startDate >= start_date AND :startDate <= expiration_date) "
				+ "OR (:expirationDate >= start_date AND :expirationDate <= expiration_date) "
				+ "OR (:startDate <= start_date AND :expirationDate >= expiration_date)",
		nativeQuery = true
	)
	Optional<PassEntity> findOverlappedPass(final boolean cancelIndicator, final String city, final Date startDate, final Date expirationDate);

}
