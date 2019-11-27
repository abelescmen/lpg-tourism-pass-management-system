package com.lpg.codechallenge.tourismpassmanagement.service.impl;

import com.lpg.codechallenge.tourismpassmanagement.entity.PassEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.VendorEntity;
import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtNotFoundException;
import com.lpg.codechallenge.tourismpassmanagement.mapper.TourismPassMgmtMapper;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import com.lpg.codechallenge.tourismpassmanagement.repository.PassRepository;
import com.lpg.codechallenge.tourismpassmanagement.repository.VendorRepository;
import com.lpg.codechallenge.tourismpassmanagement.service.VendoerService;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Service
public class VendoerServiceImpl implements VendoerService {

	private static final String ERROR_MESSAGE_VENDOR_NOT_EXISTS = "Vendor with ID ['%s'] does not exist!";
	private static final String ERROR_MESSAGE_PASS_NOT_EXISTS = "Pass with ID ['%s'] does not exist!";

	@NonNull
	private final TourismPassMgmtMapper mapper = Mappers.getMapper(TourismPassMgmtMapper.class);

	@NonNull
	private VendorRepository vendorRepository;

	@NonNull
	private PassRepository passRepository;

	/**
	 * Validate an existing Pass by Pass Id and Vendor ID
	 *
	 * @param vendorId
	 * @param passId
	 * @return Pass
	 * @throws TourismPassMgmtNotFoundException
	 */
	public Pass validatePass(final UUID vendorId, final UUID passId) throws TourismPassMgmtNotFoundException {
		log.debug("Vendor.validatePass ::: vendorId [{}] | passId [{}]", vendorId, passId);

		final VendorEntity vendorEntity = vendorRepository.findById(vendorId).orElseThrow(() -> new TourismPassMgmtNotFoundException(String.format(ERROR_MESSAGE_VENDOR_NOT_EXISTS, vendorId)));
		final PassEntity passEntity = passRepository.findByIdAndVendor(passId, vendorEntity).orElseThrow(() -> new TourismPassMgmtNotFoundException(String.format(ERROR_MESSAGE_PASS_NOT_EXISTS, passId)));

		return mapper.toPass(passEntity);
	}

}
