package com.switcher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@IdClass(FeatureID.class)
@Table(name = "feature_access")
public class FeatureAccess {

	@Id
	@Column(name = "feature_name", nullable = false)
	@NotEmpty
	String featureName;

	@Id
	@Column(name = "email", nullable = false)
	@NotEmpty
	String email;

	@Column(name = "enable")
	boolean enable;

	public FeatureAccess() {

	}

	public FeatureAccess(String featureName, String email, boolean enable) {
		super();
		this.featureName = featureName;
		this.email = email;
		this.enable = enable;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
