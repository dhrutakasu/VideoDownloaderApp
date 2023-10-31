package com.app.videodownloaderapp.Unknown;

import com.google.gson.annotations.SerializedName;

public class IgArtist{

	@SerializedName("is_private")
	private boolean isPrivate;

	@SerializedName("pk_id")
	private String pkId;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("profile_pic_id")
	private String profilePicId;

	@SerializedName("pk")
	private int pk;

	@SerializedName("is_verified")
	private boolean isVerified;

	@SerializedName("profile_pic_url")
	private String profilePicUrl;

	@SerializedName("strong_id__")
	private String strongId;

	@SerializedName("username")
	private String username;

	public void setIsPrivate(boolean isPrivate){
		this.isPrivate = isPrivate;
	}

	public boolean isIsPrivate(){
		return isPrivate;
	}

	public void setPkId(String pkId){
		this.pkId = pkId;
	}

	public String getPkId(){
		return pkId;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setProfilePicId(String profilePicId){
		this.profilePicId = profilePicId;
	}

	public String getProfilePicId(){
		return profilePicId;
	}

	public void setPk(int pk){
		this.pk = pk;
	}

	public int getPk(){
		return pk;
	}

	public void setIsVerified(boolean isVerified){
		this.isVerified = isVerified;
	}

	public boolean isIsVerified(){
		return isVerified;
	}

	public void setProfilePicUrl(String profilePicUrl){
		this.profilePicUrl = profilePicUrl;
	}

	public String getProfilePicUrl(){
		return profilePicUrl;
	}

	public void setStrongId(String strongId){
		this.strongId = strongId;
	}

	public String getStrongId(){
		return strongId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"IgArtist{" + 
			"is_private = '" + isPrivate + '\'' + 
			",pk_id = '" + pkId + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",profile_pic_id = '" + profilePicId + '\'' + 
			",pk = '" + pk + '\'' + 
			",is_verified = '" + isVerified + '\'' + 
			",profile_pic_url = '" + profilePicUrl + '\'' + 
			",strong_id__ = '" + strongId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}