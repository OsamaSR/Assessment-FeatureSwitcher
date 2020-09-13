package com.switcher.model;

public class FeatureAccessResponse {

	boolean canAccess;

	public FeatureAccessResponse(boolean canAccess) {
		this.canAccess = canAccess;
	}

	public boolean isCanAccess() {
		return canAccess;
	}

	public void setCanAccess(boolean canAccess) {
		this.canAccess = canAccess;
	}

}
