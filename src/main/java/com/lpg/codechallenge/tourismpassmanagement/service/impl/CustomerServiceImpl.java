package com.lpg.codechallenge.tourismpassmanagement.service.impl;

import com.lpg.codechallenge.tourismpassmanagement.entity.CustomerEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.PassEntity;
import com.lpg.codechallenge.tourismpassmanagement.entity.VendorEntity;
import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtNotFoundException;
import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtPreconditionFailedException;
import com.lpg.codechallenge.tourismpassmanagement.mapper.TourismPassMgmtMapper;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import com.lpg.codechallenge.tourismpassmanagement.repository.CustomerRepository;
import com.lpg.codechallenge.tourismpassmanagement.repository.PassRepository;
import com.lpg.codechallenge.tourismpassmanagement.repository.VendorRepository;
import com.lpg.codechallenge.tourismpassmanagement.service.CustomerService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final String ERROR_MESSAGE_CUSTOMER_NOT_EXISTS = "Customer with ID ['%s'] does not exist!";
	private static final String ERROR_MESSAGE_VENDOR_NOT_EXISTS = "Vendor with ID ['%s'] does not exist!";
	private static final String ERROR_MESSAGE_PASS_NOT_EXISTS = "Pass with ID ['%s'] does not exist!";
	private static final String ERROR_MESSAGE_PASS_OVERLAPPED = "Pass with city ['%s'] | startDate ['%s'] | expirationDate ['%s'] overlaps an exiting Pass!";

	@NonNull
	private final TourismPassMgmtMapper mapper = Mappers.getMapper(TourismPassMgmtMapper.class);

	@NonNull
	private CustomerRepository customerRepository;

	@NonNull
	private VendorRepository vendorRepository;

	@NonNull
	private PassRepository passRepository;

	/**
	 * Add a new Pass
	 *
	 * @param pass
	 * @return Pass
	 * @throws TourismPassMgmtNotFoundException
	 * @throws TourismPassMgmtPreconditionFailedException
	 */
	public Pass addPass(final Pass pass) throws TourismPassMgmtNotFoundException, TourismPassMgmtPreconditionFailedException {
		log.debug("Customer.addPass ::: passId [{}] | city [{}] | length [{}] | cancelIndicator [{}] | startDate [{}] | customerId [{}] | vendorId [{}]",
			pass.getId(), pass.getCity(), pass.getLength(), pass.isCancelIndicator(), pass.getStartDate(), pass.getCustomer().getId(), pass.getVendor().getId());

		if (!customerRepository.findById(pass.getCustomer().getId()).isPresent()) {
			throw new TourismPassMgmtNotFoundException(String.format(ERROR_MESSAGE_CUSTOMER_NOT_EXISTS, pass.getCustomer().getId()));
		}
		if (!vendorRepository.findById(pass.getVendor().getId()).isPresent()) {
			throw new TourismPassMgmtNotFoundException(String.format(ERROR_MESSAGE_VENDOR_NOT_EXISTS, pass.getVendor().getId()));
		}
		if (isOverlappedPass(pass)) {
			final String message = String.format(ERROR_MESSAGE_PASS_OVERLAPPED, pass.getCity(), pass.getStartDate(), calulateExpirationDate(pass.getStartDate(), pass.getLength()));
			throw new TourismPassMgmtPreconditionFailedException(message);
		}

		final PassEntity passEntity = passRepository.save(mapper.toPassEntity(pass));
		return mapper.toPass(passEntity);
	}

	/**
	 * Find an existing Pass by ID
	 *
	 * @param passId
	 * @return Pass
	 */
	public Pass findPassById(final UUID passId) {
		log.debug("Customer.findPassById ::: passId [{}]", passId);
		return passRepository.findById(passId).map(mapper::toPass).orElse(null);
	}

	/**
	 * Find existing Passes by Customer ID
	 *
	 * @param customerId
	 * @return List<Pass>
	 */
	public List<Pass> findPassesByCustomerId(final UUID customerId) {
		log.debug("Customer.findPassesByCustomerId ::: customerId [{}]", customerId);
		final CustomerEntity customerEntity = CustomerEntity.builder().id(customerId).build();
		return passRepository.findByCustomer(customerEntity).stream().map(mapper::toPass).collect(Collectors.toList());
	}

	/**
	 * Find existing Passes by Vendor ID
	 *
	 * @param vendorId
	 * @return List<Pass>
	 */
	public List<Pass> findPassesByVendorId(final UUID vendorId) {
		log.debug("Customer.findPassesByVendorId ::: vendorId [{}]", vendorId);
		final VendorEntity vendorEntity = VendorEntity.builder().id(vendorId).build();
		return passRepository.findByVendor(vendorEntity).stream().map(mapper::toPass).collect(Collectors.toList());
	}

	/**
	 * Find all existing Passes
	 *
	 * @return List<Pass>
	 */
	public List<Pass> findAllPasses() {
		log.debug("Customer.findAllPasses");
		return passRepository.findAll().stream().map(mapper::toPass).collect(Collectors.toList());
	}

	/**
	 * Cancel an existing Pass
	 *
	 * @param passId
	 * @throws TourismPassMgmtNotFoundException
	 */
	public void cancelPass(final UUID passId) throws TourismPassMgmtNotFoundException {
		log.debug("Customer.cancelPass ::: passId [{}]", passId);

		final PassEntity passEntity = passRepository.findById(passId).orElseThrow(() -> new TourismPassMgmtNotFoundException(String.format(ERROR_MESSAGE_PASS_NOT_EXISTS, passId)));
		passEntity.setCancelIndicator(true);

		passRepository.save(passEntity);
	}

	/**
	 * Renew an existing Pass
	 *
	 * @param pass
	 * @return Pass
	 * @throws TourismPassMgmtNotFoundException
	 * @throws TourismPassMgmtPreconditionFailedException
	 */
	public Pass renewPass(final Pass pass) throws TourismPassMgmtNotFoundException, TourismPassMgmtPreconditionFailedException {
		log.debug("Customer.renewPass ::: passId [{}] | city [{}] | length [{}] | cancelIndicator [{}] | startDate [{}] | customerId [{}] | vendorId [{}]",
			pass.getId(), pass.getCity(), pass.getLength(), pass.isCancelIndicator(), pass.getStartDate(), pass.getCustomer().getId(), pass.getVendor().getId());

		PassEntity passEntity = passRepository.findById(pass.getId()).orElseThrow(() -> new TourismPassMgmtNotFoundException(String.format(ERROR_MESSAGE_PASS_NOT_EXISTS, pass.getId())));
		if (isOverlappedPass(pass)) {
			final String message = String.format(ERROR_MESSAGE_PASS_OVERLAPPED, pass.getCity(), pass.getStartDate(), calulateExpirationDate(pass.getStartDate(), pass.getLength()));
			throw new TourismPassMgmtPreconditionFailedException(message);
		}

		passEntity.setCity(pass.getCity());
		passEntity.setStartDate(Date.valueOf(pass.getStartDate()));
		passEntity.setLength(pass.getLength());
		passEntity.setCancelIndicator(false);
		passEntity = passRepository.save(passEntity);

		return mapper.toPass(passEntity);
	}

	private boolean isOverlappedPass(final Pass pass) {
		pass.setExpirationDate(calulateExpirationDate(pass.getStartDate(), pass.getLength()));
		final Optional<PassEntity> optionalPass = passRepository.findOverlappedPass(false, pass.getCity(), Date.valueOf(pass.getStartDate()), Date.valueOf(pass.getExpirationDate()));
		return optionalPass.isPresent();
	}

	private LocalDate calulateExpirationDate(final LocalDate startDate, final Integer length) {
		return startDate.plusDays(length);
	}

}
