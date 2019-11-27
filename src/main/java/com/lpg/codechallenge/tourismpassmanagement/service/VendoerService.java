package com.lpg.codechallenge.tourismpassmanagement.service;

import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtNotFoundException;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import java.util.UUID;

public interface VendoerService {

	/**
	 * Validate an existing Pass by Pass Id and Vendor ID
	 *
	 * @param vendorId
	 * @param passId
	 * @return Pass
	 * @throws TourismPassMgmtNotFoundException
	 */
	Pass validatePass(final UUID vendorId, final UUID passId) throws TourismPassMgmtNotFoundException;
	
}
