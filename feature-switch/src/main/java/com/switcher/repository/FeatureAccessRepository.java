package com.switcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.switcher.model.FeatureAccess;
import com.switcher.model.FeatureID;

@Repository
public interface FeatureAccessRepository extends JpaRepository<FeatureAccess, FeatureID>{
	
//	FeatureAccess findByFeatureNameIgnoreCaseAndEmailIgnoreCase(String featureName, String email);
	
//	Optional<FeatureAccess> findByIDIgnoreCase(FeatureID id);

}
