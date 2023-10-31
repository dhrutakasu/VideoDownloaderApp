package com.app.videodownloaderapp.unknown2;

import com.google.gson.annotations.SerializedName;

public class FanClubInfo{

	@SerializedName("fan_club_id")
	private Object fanClubId;

	@SerializedName("subscriber_count")
	private Object subscriberCount;

	@SerializedName("is_fan_club_referral_eligible")
	private Object isFanClubReferralEligible;

	@SerializedName("fan_consideration_page_revamp_eligiblity")
	private Object fanConsiderationPageRevampEligiblity;

	@SerializedName("fan_club_name")
	private Object fanClubName;

	@SerializedName("connected_member_count")
	private Object connectedMemberCount;

	@SerializedName("autosave_to_exclusive_highlight")
	private Object autosaveToExclusiveHighlight;

	@SerializedName("has_enough_subscribers_for_ssc")
	private Object hasEnoughSubscribersForSsc;

	@SerializedName("is_fan_club_gifting_eligible")
	private Object isFanClubGiftingEligible;

	public void setFanClubId(Object fanClubId){
		this.fanClubId = fanClubId;
	}

	public Object getFanClubId(){
		return fanClubId;
	}

	public void setSubscriberCount(Object subscriberCount){
		this.subscriberCount = subscriberCount;
	}

	public Object getSubscriberCount(){
		return subscriberCount;
	}

	public void setIsFanClubReferralEligible(Object isFanClubReferralEligible){
		this.isFanClubReferralEligible = isFanClubReferralEligible;
	}

	public Object getIsFanClubReferralEligible(){
		return isFanClubReferralEligible;
	}

	public void setFanConsiderationPageRevampEligiblity(Object fanConsiderationPageRevampEligiblity){
		this.fanConsiderationPageRevampEligiblity = fanConsiderationPageRevampEligiblity;
	}

	public Object getFanConsiderationPageRevampEligiblity(){
		return fanConsiderationPageRevampEligiblity;
	}

	public void setFanClubName(Object fanClubName){
		this.fanClubName = fanClubName;
	}

	public Object getFanClubName(){
		return fanClubName;
	}

	public void setConnectedMemberCount(Object connectedMemberCount){
		this.connectedMemberCount = connectedMemberCount;
	}

	public Object getConnectedMemberCount(){
		return connectedMemberCount;
	}

	public void setAutosaveToExclusiveHighlight(Object autosaveToExclusiveHighlight){
		this.autosaveToExclusiveHighlight = autosaveToExclusiveHighlight;
	}

	public Object getAutosaveToExclusiveHighlight(){
		return autosaveToExclusiveHighlight;
	}

	public void setHasEnoughSubscribersForSsc(Object hasEnoughSubscribersForSsc){
		this.hasEnoughSubscribersForSsc = hasEnoughSubscribersForSsc;
	}

	public Object getHasEnoughSubscribersForSsc(){
		return hasEnoughSubscribersForSsc;
	}

	public void setIsFanClubGiftingEligible(Object isFanClubGiftingEligible){
		this.isFanClubGiftingEligible = isFanClubGiftingEligible;
	}

	public Object getIsFanClubGiftingEligible(){
		return isFanClubGiftingEligible;
	}

	@Override
 	public String toString(){
		return 
			"FanClubInfo{" + 
			"fan_club_id = '" + fanClubId + '\'' + 
			",subscriber_count = '" + subscriberCount + '\'' + 
			",is_fan_club_referral_eligible = '" + isFanClubReferralEligible + '\'' + 
			",fan_consideration_page_revamp_eligiblity = '" + fanConsiderationPageRevampEligiblity + '\'' + 
			",fan_club_name = '" + fanClubName + '\'' + 
			",connected_member_count = '" + connectedMemberCount + '\'' + 
			",autosave_to_exclusive_highlight = '" + autosaveToExclusiveHighlight + '\'' + 
			",has_enough_subscribers_for_ssc = '" + hasEnoughSubscribersForSsc + '\'' + 
			",is_fan_club_gifting_eligible = '" + isFanClubGiftingEligible + '\'' + 
			"}";
		}
}