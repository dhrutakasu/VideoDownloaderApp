package com.app.videodownloaderapp.unknown2;

import com.google.gson.annotations.SerializedName;

public class Caption{

	@SerializedName("private_reply_status")
	private int privateReplyStatus;

	@SerializedName("share_enabled")
	private boolean shareEnabled;

	@SerializedName("created_at")
	private int createdAt;

	@SerializedName("type")
	private int type;

	@SerializedName("is_covered")
	private boolean isCovered;

	@SerializedName("created_at_utc")
	private int createdAtUtc;

	@SerializedName("content_type")
	private String contentType;

	@SerializedName("user_id")
	private long userId;

	@SerializedName("bit_flags")
	private int bitFlags;

	@SerializedName("media_id")
	private long mediaId;

	@SerializedName("pk")
	private String pk;

	@SerializedName("text")
	private String text;

	@SerializedName("did_report_as_spam")
	private boolean didReportAsSpam;

	@SerializedName("user")
	private User user;

	@SerializedName("is_ranked_comment")
	private boolean isRankedComment;

	@SerializedName("status")
	private String status;

	public void setPrivateReplyStatus(int privateReplyStatus){
		this.privateReplyStatus = privateReplyStatus;
	}

	public int getPrivateReplyStatus(){
		return privateReplyStatus;
	}

	public void setShareEnabled(boolean shareEnabled){
		this.shareEnabled = shareEnabled;
	}

	public boolean isShareEnabled(){
		return shareEnabled;
	}

	public void setCreatedAt(int createdAt){
		this.createdAt = createdAt;
	}

	public int getCreatedAt(){
		return createdAt;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setIsCovered(boolean isCovered){
		this.isCovered = isCovered;
	}

	public boolean isIsCovered(){
		return isCovered;
	}

	public void setCreatedAtUtc(int createdAtUtc){
		this.createdAtUtc = createdAtUtc;
	}

	public int getCreatedAtUtc(){
		return createdAtUtc;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}

	public long getUserId(){
		return userId;
	}

	public void setBitFlags(int bitFlags){
		this.bitFlags = bitFlags;
	}

	public int getBitFlags(){
		return bitFlags;
	}

	public void setMediaId(long mediaId){
		this.mediaId = mediaId;
	}

	public long getMediaId(){
		return mediaId;
	}

	public void setPk(String pk){
		this.pk = pk;
	}

	public String getPk(){
		return pk;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setDidReportAsSpam(boolean didReportAsSpam){
		this.didReportAsSpam = didReportAsSpam;
	}

	public boolean isDidReportAsSpam(){
		return didReportAsSpam;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setIsRankedComment(boolean isRankedComment){
		this.isRankedComment = isRankedComment;
	}

	public boolean isIsRankedComment(){
		return isRankedComment;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Caption{" + 
			"private_reply_status = '" + privateReplyStatus + '\'' + 
			",share_enabled = '" + shareEnabled + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",type = '" + type + '\'' + 
			",is_covered = '" + isCovered + '\'' + 
			",created_at_utc = '" + createdAtUtc + '\'' + 
			",content_type = '" + contentType + '\'' + 
			",user_id = '" + userId + '\'' + 
			",bit_flags = '" + bitFlags + '\'' + 
			",media_id = '" + mediaId + '\'' + 
			",pk = '" + pk + '\'' + 
			",text = '" + text + '\'' + 
			",did_report_as_spam = '" + didReportAsSpam + '\'' + 
			",user = '" + user + '\'' + 
			",is_ranked_comment = '" + isRankedComment + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}