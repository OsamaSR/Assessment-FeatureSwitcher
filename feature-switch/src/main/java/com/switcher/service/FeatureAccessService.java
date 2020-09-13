package com.switcher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.switcher.model.FeatureAccess;
import com.switcher.model.FeatureID;
import com.switcher.repository.FeatureAccessRepository;

@Service
public class FeatureAccessService {

	@Autowired
	private FeatureAccessRepository featureAccessRepository;

	public boolean createOrUpdateFeatureAccess(FeatureAccess featureAccess) {

		featureAccessRepository.save(featureAccess);

		return true;
	}

	public boolean checkAccess(String featureName, String email) {
		
		Optional<FeatureAccess> existingFA = featureAccessRepository.findById(new FeatureID(featureName, email));
		
		if(existingFA.isPresent()) {
			return existingFA.get().isEnable();
		}
		System.out.println("feature access not found");
		return false;
	}

	public List<FeatureAccess> getAll() {
		return featureAccessRepository.findAll();
	}

}
