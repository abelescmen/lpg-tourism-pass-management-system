package com.lpg.codechallenge.tourismpassmanagement.controller;

import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtNotFoundException;
import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtPreconditionFailedException;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import com.lpg.codechallenge.tourismpassmanagement.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Customers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CustomerController {

	@NonNull
	private CustomerService customerService;

	/**
	 * Add a new Pass
	 * <p>
	 *
	 * @param pass
	 * @return ResponseEntity<Pass>
	 * @throws TourismPassMgmtNotFoundException
	 * @throws TourismPassMgmtPreconditionFailedException
	 */
	@ApiOperation(
		value = "Add a new Pass",
		notes = "Add a new Pass"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Pass added successfully"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Customer/Vendor Not Found"),
		@ApiResponse(code = 409, message = "Pass overlapped")
	})
	@PostMapping(value = "/customers/passes")
	public ResponseEntity<Pass> addPass(
			@ApiParam(value = "Pass to add") @RequestBody final Pass pass)
			throws TourismPassMgmtNotFoundException, TourismPassMgmtPreconditionFailedException {
		return new ResponseEntity<>(customerService.addPass(pass), HttpStatus.CREATED);
	}

	/**
	 * Find an existing Pass by ID
	 * <p>
	 *
	 * @param passId
	 * @return ResponseEntity<Pass>
	 */
	@ApiOperation(
		value = "Find existing Passes by ID",
		notes = "find existing Passes using its ID"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@GetMapping(value = "/customers/passes/{passId}")
	public ResponseEntity<Pass> findPassById(
			@ApiParam(value = "Pass ID") @PathVariable(value = "passId") final UUID passId) {
		return new ResponseEntity<>(customerService.findPassById(passId), HttpStatus.OK);
	}

	/**
	 * Find existinging Passes by Customer ID
	 * <p>
	 *
	 * @param customerId
	 * @return ResponseEntity<List<Pass>>
	 */
	@ApiOperation(
		value = "Find existing Passes by Customer ID",
		notes = "find existing Passes using Customer ID"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@GetMapping(value = "/customers/passes/customers/{customerId}")
	public ResponseEntity<List<Pass>> findPassByCustomerId(
			@ApiParam(value = "Customer ID") @PathVariable(value = "customerId") final UUID customerId)  {
		return new ResponseEntity<>(customerService.findPassesByCustomerId(customerId), HttpStatus.OK);
	}

	/**
	 * Find existinging Passes by Vendor ID
	 * <p>
	 *
	 * @param vendorId
	 * @return ResponseEntity<List<Pass>>
	 */
	@ApiOperation(
		value = "Find an existing Pass by Vendor ID",
		notes = "find an existing Pass using Vendor ID"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@GetMapping(value = "/customers/passes/vendor/{vendorId}")
	public ResponseEntity<List<Pass>> findPassByVendorId(
			@ApiParam(value = "Vendor ID") @PathVariable(value = "vendorId") final UUID vendorId)  {
		return new ResponseEntity<>(customerService.findPassesByVendorId(vendorId), HttpStatus.OK);
	}

	/**
	 * Find all existing Passes
	 * <p>
	 *
	 * @param all
	 * @return ResponseEntity<Pass>
	 */
	@ApiOperation(
		value = "Find all existing Passes",
		notes = "Find all existing Passes"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@GetMapping(value = "/customers/passes")
	public ResponseEntity<List<Pass>> findAllPass(
			@ApiParam(name = "all", value = "true", required = true) @RequestParam(value = "all", defaultValue = "true") final boolean all) {
		return new ResponseEntity<>(customerService.findAllPasses(), HttpStatus.OK);
	}

	/**
	 * Cancel an existing Pass
	 * <p>
	 *
	 * @param passId
	 * @return ResponseEntity<Pass>
	 * @throws TourismPassMgmtNotFoundException
	 */
	@ApiOperation(
		value = "Cancel an existing Pass",
		notes = "Cancel an existing Pass"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Pass cancelled successfully"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Pass Not Found")
	})
	@DeleteMapping(value = "/customers/passes/{passId}")
	public ResponseEntity<Void> cancelPass(
			@ApiParam(value = "Pass ID to Cancel")  @PathVariable(value = "passId") final UUID passId)
			throws TourismPassMgmtNotFoundException {
		customerService.cancelPass(passId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Renew an existing Pass
	 * <p>
	 *
	 * @param pass
	 * @return ResponseEntity<Pass>
	 * @throws TourismPassMgmtNotFoundException
	 * @throws TourismPassMgmtPreconditionFailedException
	 */
	@ApiOperation(
		value = "Renew an existing Pass",
		notes = "Renew an existing Pass"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 202, message = "Pass renewed successfully"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Pass/Customer/Vendor Not Found"),
		@ApiResponse(code = 409, message = "Pass overlapped")
	})
	@PutMapping(value = "/customers/passes")
	public ResponseEntity<Pass> renewPass(
			@ApiParam(value = "Pass to renew") @RequestBody final Pass pass)
			throws TourismPassMgmtNotFoundException, TourismPassMgmtPreconditionFailedException {
		return new ResponseEntity<>(customerService.renewPass(pass), HttpStatus.ACCEPTED);
	}

}
