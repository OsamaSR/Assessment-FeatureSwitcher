package com.example.switcher.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.switcher.FeatureSwitchApplication;
import com.switcher.controller.SwitchController;
import com.switcher.model.FeatureAccess;
import com.switcher.service.FeatureAccessService;

//@SpringBootTest
@ContextConfiguration(classes = FeatureSwitchApplication.class)
@WebMvcTest(SwitchController.class)
@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
public class SwitchControllerTest {

	@Autowired
	private MockMvc mvc;

	private String requestBody = "{\r\n" + "\"featureName\": \"test\",\r\n" + "\"email\": \"test\", \r\n"
			+ "\"enable\": true\r\n" + "}";

	@MockBean
	private FeatureAccessService service;

	@Test
	public void testPost() throws Exception {

		Mockito.when(service.createOrUpdateFeatureAccess(Mockito.any(FeatureAccess.class))).thenReturn(true);

		RequestBuilder request = MockMvcRequestBuilders.post("/feature").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody);

		mvc.perform(request).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void test_if_create_failed_return_304() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/feature").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody);

		Mockito.when(service.createOrUpdateFeatureAccess(Mockito.any(FeatureAccess.class))).thenReturn(false);

		mvc.perform(request).andExpect(status().isNotModified()).andReturn();

	}

	@Test
	public void testGet() throws Exception {

		String featureName = "feature1";
		String email = "email1";

		RequestBuilder request = MockMvcRequestBuilders.get("/feature").param("email", email).param("featureName",
				featureName);

		Mockito.when(service.checkAccess(featureName, email)).thenReturn(true);

		MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();

		assertEquals("{\"canAccess\":true}", result.getResponse().getContentAsString());

	}
	
	@Test
	public void testMissingParam() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.post("/feature").contentType(MediaType.APPLICATION_JSON)
				.content("{\"featureName\":\"test\"}");

		mvc.perform(request).andExpect(status().isNotModified()).andReturn();
	}
	
	
	
}
