package com.app.videodownloaderapp.unknown2;

import com.google.gson.annotations.SerializedName;

public class FriendshipStatus{

	@SerializedName("is_restricted")
	private boolean isRestricted;

	@SerializedName("following")
	private boolean following;

	@SerializedName("is_feed_favorite")
	private boolean isFeedFavorite;

	@SerializedName("outgoing_request")
	private boolean outgoingRequest;

	@SerializedName("is_bestie")
	private boolean isBestie;

	public void setIsRestricted(boolean isRestricted){
		this.isRestricted = isRestricted;
	}

	public boolean isIsRestricted(){
		return isRestricted;
	}

	public void setFollowing(boolean following){
		this.following = following;
	}

	public boolean isFollowing(){
		return following;
	}

	public void setIsFeedFavorite(boolean isFeedFavorite){
		this.isFeedFavorite = isFeedFavorite;
	}

	public boolean isIsFeedFavorite(){
		return isFeedFavorite;
	}

	public void setOutgoingRequest(boolean outgoingRequest){
		this.outgoingRequest = outgoingRequest;
	}

	public boolean isOutgoingRequest(){
		return outgoingRequest;
	}

	public void setIsBestie(boolean isBestie){
		this.isBestie = isBestie;
	}

	public boolean isIsBestie(){
		return isBestie;
	}

	@Override
 	public String toString(){
		return 
			"FriendshipStatus{" + 
			"is_restricted = '" + isRestricted + '\'' + 
			",following = '" + following + '\'' + 
			",is_feed_favorite = '" + isFeedFavorite + '\'' + 
			",outgoing_request = '" + outgoingRequest + '\'' + 
			",is_bestie = '" + isBestie + '\'' + 
			"}";
		}
}