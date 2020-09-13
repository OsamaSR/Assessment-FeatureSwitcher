package com.switcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.switcher.exception.UpdateException;
import com.switcher.model.FeatureAccess;
import com.switcher.model.FeatureAccessResponse;
import com.switcher.service.FeatureAccessService;

@RestController
public class SwitchController {

	@Autowired
	private FeatureAccessService featureAccessService;

	@GetMapping("/feature")
	public FeatureAccessResponse checkAccess(@RequestParam(value = "email") String email,
			@RequestParam(value = "featureName") String featureName) {
		return new FeatureAccessResponse(featureAccessService.checkAccess(featureName, email));
	}

//	@GetMapping("/feature/all")	
//	public List<FeatureAccess> getAll() {
//		return featureAccessService.getAll();
//	}

	@PostMapping("/feature")
	public ResponseEntity<Void> updateAccess(@RequestBody FeatureAccess featureAccess) {
		try {
			if (featureAccessService.createOrUpdateFeatureAccess(featureAccess))
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		} catch (Exception e) {
			throw new UpdateException();
		}
	}

	@ExceptionHandler(UpdateException.class)
	public ResponseEntity<Void> handleError(UpdateException e) {

		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	}

}