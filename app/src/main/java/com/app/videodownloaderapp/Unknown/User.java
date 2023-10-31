package com.app.videodownloaderapp.Unknown;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("is_private")
	private boolean isPrivate;

	@SerializedName("account_badges")
	private List<Object> accountBadges;

	@SerializedName("pk_id")
	private String pkId;

	@SerializedName("is_favorite")
	private boolean isFavorite;

	@SerializedName("show_account_transparency_details")
	private boolean showAccountTransparencyDetails;

	@SerializedName("profile_pic_id")
	private String profilePicId;

	@SerializedName("third_party_downloads_enabled")
	private int thirdPartyDownloadsEnabled;

	@SerializedName("fbid_v2")
	private long fbidV2;

	@SerializedName("transparency_product_enabled")
	private boolean transparencyProductEnabled;

	@SerializedName("fan_club_info")
	private FanClubInfo fanClubInfo;

	@SerializedName("has_anonymous_profile_picture")
	private boolean hasAnonymousProfilePicture;

	@SerializedName("hd_profile_pic_url_info")
	private HdProfilePicUrlInfo hdProfilePicUrlInfo;

	@SerializedName("is_verified")
	private boolean isVerified;

	@SerializedName("hd_profile_pic_versions")
	private List<HdProfilePicVersionsItem> hdProfilePicVersions;

	@SerializedName("friendship_status")
	private FriendshipStatus friendshipStatus;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("is_unpublished")
	private boolean isUnpublished;

	@SerializedName("feed_post_reshare_disabled")
	private boolean feedPostReshareDisabled;

	@SerializedName("id")
	private String id;

	@SerializedName("pk")
	private long pk;

	@SerializedName("latest_reel_media")
	private int latestReelMedia;

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

	public void setAccountBadges(List<Object> accountBadges){
		this.accountBadges = accountBadges;
	}

	public List<Object> getAccountBadges(){
		return accountBadges;
	}

	public void setPkId(String pkId){
		this.pkId = pkId;
	}

	public String getPkId(){
		return pkId;
	}

	public void setIsFavorite(boolean isFavorite){
		this.isFavorite = isFavorite;
	}

	public boolean isIsFavorite(){
		return isFavorite;
	}

	public void setShowAccountTransparencyDetails(boolean showAccountTransparencyDetails){
		this.showAccountTransparencyDetails = showAccountTransparencyDetails;
	}

	public boolean isShowAccountTransparencyDetails(){
		return showAccountTransparencyDetails;
	}

	public void setProfilePicId(String profilePicId){
		this.profilePicId = profilePicId;
	}

	public String getProfilePicId(){
		return profilePicId;
	}

	public void setThirdPartyDownloadsEnabled(int thirdPartyDownloadsEnabled){
		this.thirdPartyDownloadsEnabled = thirdPartyDownloadsEnabled;
	}

	public int getThirdPartyDownloadsEnabled(){
		return thirdPartyDownloadsEnabled;
	}

	public void setFbidV2(long fbidV2){
		this.fbidV2 = fbidV2;
	}

	public long getFbidV2(){
		return fbidV2;
	}

	public void setTransparencyProductEnabled(boolean transparencyProductEnabled){
		this.transparencyProductEnabled = transparencyProductEnabled;
	}

	public boolean isTransparencyProductEnabled(){
		return transparencyProductEnabled;
	}

	public void setFanClubInfo(FanClubInfo fanClubInfo){
		this.fanClubInfo = fanClubInfo;
	}

	public FanClubInfo getFanClubInfo(){
		return fanClubInfo;
	}

	public void setHasAnonymousProfilePicture(boolean hasAnonymousProfilePicture){
		this.hasAnonymousProfilePicture = hasAnonymousProfilePicture;
	}

	public boolean isHasAnonymousProfilePicture(){
		return hasAnonymousProfilePicture;
	}

	public void setHdProfilePicUrlInfo(HdProfilePicUrlInfo hdProfilePicUrlInfo){
		this.hdProfilePicUrlInfo = hdProfilePicUrlInfo;
	}

	public HdProfilePicUrlInfo getHdProfilePicUrlInfo(){
		return hdProfilePicUrlInfo;
	}

	public void setIsVerified(boolean isVerified){
		this.isVerified = isVerified;
	}

	public boolean isIsVerified(){
		return isVerified;
	}

	public void setHdProfilePicVersions(List<HdProfilePicVersionsItem> hdProfilePicVersions){
		this.hdProfilePicVersions = hdProfilePicVersions;
	}

	public List<HdProfilePicVersionsItem> getHdProfilePicVersions(){
		return hdProfilePicVersions;
	}

	public void setFriendshipStatus(FriendshipStatus friendshipStatus){
		this.friendshipStatus = friendshipStatus;
	}

	public FriendshipStatus getFriendshipStatus(){
		return friendshipStatus;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setIsUnpublished(boolean isUnpublished){
		this.isUnpublished = isUnpublished;
	}

	public boolean isIsUnpublished(){
		return isUnpublished;
	}

	public void setFeedPostReshareDisabled(boolean feedPostReshareDisabled){
		this.feedPostReshareDisabled = feedPostReshareDisabled;
	}

	public boolean isFeedPostReshareDisabled(){
		return feedPostReshareDisabled;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPk(long pk){
		this.pk = pk;
	}

	public long getPk(){
		return pk;
	}

	public void setLatestReelMedia(int latestReelMedia){
		this.latestReelMedia = latestReelMedia;
	}

	public int getLatestReelMedia(){
		return latestReelMedia;
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
			"User{" + 
			"is_private = '" + isPrivate + '\'' + 
			",account_badges = '" + accountBadges + '\'' + 
			",pk_id = '" + pkId + '\'' + 
			",is_favorite = '" + isFavorite + '\'' + 
			",show_account_transparency_details = '" + showAccountTransparencyDetails + '\'' + 
			",profile_pic_id = '" + profilePicId + '\'' + 
			",third_party_downloads_enabled = '" + thirdPartyDownloadsEnabled + '\'' + 
			",fbid_v2 = '" + fbidV2 + '\'' + 
			",transparency_product_enabled = '" + transparencyProductEnabled + '\'' + 
			",fan_club_info = '" + fanClubInfo + '\'' + 
			",has_anonymous_profile_picture = '" + hasAnonymousProfilePicture + '\'' + 
			",hd_profile_pic_url_info = '" + hdProfilePicUrlInfo + '\'' + 
			",is_verified = '" + isVerified + '\'' + 
			",hd_profile_pic_versions = '" + hdProfilePicVersions + '\'' + 
			",friendship_status = '" + friendshipStatus + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",is_unpublished = '" + isUnpublished + '\'' + 
			",feed_post_reshare_disabled = '" + feedPostReshareDisabled + '\'' + 
			",id = '" + id + '\'' + 
			",pk = '" + pk + '\'' + 
			",latest_reel_media = '" + latestReelMedia + '\'' + 
			",profile_pic_url = '" + profilePicUrl + '\'' + 
			",strong_id__ = '" + strongId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}