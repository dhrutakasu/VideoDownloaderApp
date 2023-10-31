package com.app.videodownloaderapp.unknown2;

import com.google.gson.annotations.SerializedName;

public class CandidatesItem{

	@SerializedName("width")
	private int width;

	@SerializedName("url")
	private String url;

	@SerializedName("height")
	private int height;

	@SerializedName("scans_profile")
	private String scansProfile;

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	public void setScansProfile(String scansProfile){
		this.scansProfile = scansProfile;
	}

	public String getScansProfile(){
		return scansProfile;
	}

	@Override
 	public String toString(){
		return 
			"CandidatesItem{" + 
			"width = '" + width + '\'' + 
			",url = '" + url + '\'' + 
			",height = '" + height + '\'' + 
			",scans_profile = '" + scansProfile + '\'' + 
			"}";
		}
}