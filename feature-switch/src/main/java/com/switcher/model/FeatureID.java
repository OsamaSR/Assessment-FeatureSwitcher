package com.switcher.model;

import java.io.Serializable;
import java.util.Objects;

public class FeatureID implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String featureName;
	private String email;

	public FeatureID() {
	}

	public FeatureID(String featureName, String email) {
		this.featureName = featureName;
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FeatureID id = (FeatureID) o;
		return featureName.equals(id.featureName) && email.equals(id.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(featureName, email);
	}
}
