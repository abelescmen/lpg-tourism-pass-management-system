package com.lpg.codechallenge.tourismpassmanagement.service;

import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtNotFoundException;
import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtPreconditionFailedException;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

	/**
	 * Add a new Pass
	 *
	 * @param pass
	 * @return Pass
	 * @throws TourismPassMgmtNotFoundException
	 * @throws TourismPassMgmtPreconditionFailedException
	 */
	Pass addPass(final Pass pass) throws TourismPassMgmtNotFoundException, TourismPassMgmtPreconditionFailedException;

	/**
	 * Find an existing Pass by ID
	 *
	 * @param passId
	 * @return Pass
	 */
	Pass findPassById(final UUID passId);

	/**
	 * Find existing Passes by Customer ID
	 *
	 * @param customerId
	 * @return List<Pass>
	 */
	List<Pass> findPassesByCustomerId(final UUID customerId);

	/**
	 * Find existing Passes by Vendor ID
	 *
	 * @param vendorId
	 * @return List<Pass>
	 */
	List<Pass> findPassesByVendorId(final UUID vendorId);

	/**
	 * Find all existing Passes
	 *
	 * @return List<Pass>
	 */
	List<Pass> findAllPasses();

	/**
	 * Cancel an existing Pass
	 *
	 * @param passId
	 * @throws TourismPassMgmtNotFoundException
	 */
	void cancelPass(final UUID passId) throws TourismPassMgmtNotFoundException;

	/**
	 * Renew an existing Pass
	 *
	 * @param pass
	 * @return Pass
	 * @throws TourismPassMgmtNotFoundException
	 * @throws TourismPassMgmtPreconditionFailedException
	 */
	Pass renewPass(final Pass pass) throws TourismPassMgmtNotFoundException, TourismPassMgmtPreconditionFailedException;

}
