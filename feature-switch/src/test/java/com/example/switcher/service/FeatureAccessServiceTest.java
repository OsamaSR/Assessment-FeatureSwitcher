package com.example.switcher.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.switcher.FeatureSwitchApplication;
import com.switcher.model.FeatureAccess;
import com.switcher.model.FeatureID;
import com.switcher.repository.FeatureAccessRepository;
import com.switcher.service.FeatureAccessService;

@SpringBootTest
@ContextConfiguration(classes = FeatureSwitchApplication.class)
@RunWith(MockitoJUnitRunner.class)
public class FeatureAccessServiceTest {
	
	@Mock
	FeatureAccessRepository repo;

	@InjectMocks
    private FeatureAccessService featureAccessService = new FeatureAccessService();
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testcreateOrUpdateFeatureAccess() {
		String featureName = "feature1";
		String email = "email1";
    	assertTrue(featureAccessService.createOrUpdateFeatureAccess(new FeatureAccess(featureName, email, true))); 
    }
    
    @Test
    public void testcheckAccess() {
		String featureName = "feature1";
		String email = "email1";
		FeatureAccess fa = new FeatureAccess(featureName, email, true);
//        Mockito.when(repo.save(Mockito.any(FeatureAccess.class))).thenReturn(fa);

        assertTrue(featureAccessService.createOrUpdateFeatureAccess(fa));
    	
        Mockito.when(repo.findById(new FeatureID(featureName, email))).thenReturn(Optional.of(fa));      

    	assertTrue(featureAccessService.checkAccess(featureName, email));
    	assertFalse(featureAccessService.checkAccess("x", "x"));
    	
    }
}
