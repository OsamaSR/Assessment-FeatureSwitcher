package com.example.switcher.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.switcher.FeatureSwitchApplication;
import com.switcher.model.FeatureAccess;
import com.switcher.model.FeatureID;
import com.switcher.repository.FeatureAccessRepository;

@DataJpaTest
@ContextConfiguration(classes = FeatureSwitchApplication.class)
public class FeatureAccessRepositoryTest {

	@Autowired
	FeatureAccessRepository repo;

	@Test
	public void testCreateFeatureAccess() {
		FeatureAccess fa = new FeatureAccess("feature1", "email1", true);
		FeatureAccess savedFA = repo.save(fa);
		assertNotNull(savedFA);
	}

	@Test
	public void testfindBy() {
		
		String featureName = "feature1";
		String email = "email1";
		FeatureAccess fa = new FeatureAccess(featureName, email, true);
		
		FeatureID id = new FeatureID(featureName, email);
		
		repo.save(fa);
			
		assertEquals(true, repo.findById(id).isPresent());		

	}
	
	@Test
	public void testUpdate() {
		String featureName = "feature1";
		String email = "email1";
		FeatureAccess fa = repo.save(new FeatureAccess(featureName, email, true));
		
		FeatureID id = new FeatureID(featureName, email);

		fa.setEnable(false);
		repo.save(fa);
		
		assertEquals(false, repo.findById(id).get().isEnable());		
		
	}
}
