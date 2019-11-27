package com.lpg.codechallenge.tourismpassmanagement.controller;

import com.lpg.codechallenge.tourismpassmanagement.exception.TourismPassMgmtNotFoundException;
import com.lpg.codechallenge.tourismpassmanagement.model.Pass;
import com.lpg.codechallenge.tourismpassmanagement.service.VendoerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Vendors")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class VendorController {

	@NonNull
	private VendoerService vendoerService;

	/**
	 * Validate an existing Pass by Pass ID and Vendor ID
	 * <p>
	 *
	 * @param vendorId
	 * @param passId
	 * @return ResponseEntity<Pass>
	 * @throws TourismPassMgmtNotFoundException
	 */
	@ApiOperation(
		value = "Validate an existing Pass by Pass ID and Vendor ID",
		notes = "Validate an existing Pass by Pass ID and Vendor ID"
	)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Vendor Not Found")
	})
	@GetMapping(value = "/vendors/{vendorId}/passes/{passId}")
	public ResponseEntity<Pass> cancelPass(
			@ApiParam(value = "Vendor ID")  @PathVariable(value = "vendorId") final UUID vendorId,
			@ApiParam(value = "Pass ID")  @PathVariable(value = "passId") final UUID passId,
			@ApiParam(name = "validate", value = "true", required = true) @RequestParam(value = "validate", defaultValue = "true") final boolean validate)
			throws TourismPassMgmtNotFoundException {
		return new ResponseEntity<>(vendoerService.validatePass(vendorId, passId), HttpStatus.OK);
	}

}
