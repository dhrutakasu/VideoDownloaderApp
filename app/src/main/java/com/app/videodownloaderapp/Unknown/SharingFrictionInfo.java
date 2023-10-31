package com.app.videodownloaderapp.Unknown;

import com.google.gson.annotations.SerializedName;

public class SharingFrictionInfo{

	@SerializedName("bloks_app_url")
	private Object bloksAppUrl;

	@SerializedName("should_have_sharing_friction")
	private boolean shouldHaveSharingFriction;

	@SerializedName("sharing_friction_payload")
	private Object sharingFrictionPayload;

	public void setBloksAppUrl(Object bloksAppUrl){
		this.bloksAppUrl = bloksAppUrl;
	}

	public Object getBloksAppUrl(){
		return bloksAppUrl;
	}

	public void setShouldHaveSharingFriction(boolean shouldHaveSharingFriction){
		this.shouldHaveSharingFriction = shouldHaveSharingFriction;
	}

	public boolean isShouldHaveSharingFriction(){
		return shouldHaveSharingFriction;
	}

	public void setSharingFrictionPayload(Object sharingFrictionPayload){
		this.sharingFrictionPayload = sharingFrictionPayload;
	}

	public Object getSharingFrictionPayload(){
		return sharingFrictionPayload;
	}

	@Override
 	public String toString(){
		return 
			"SharingFrictionInfo{" + 
			"bloks_app_url = '" + bloksAppUrl + '\'' + 
			",should_have_sharing_friction = '" + shouldHaveSharingFriction + '\'' + 
			",sharing_friction_payload = '" + sharingFrictionPayload + '\'' + 
			"}";
		}
}