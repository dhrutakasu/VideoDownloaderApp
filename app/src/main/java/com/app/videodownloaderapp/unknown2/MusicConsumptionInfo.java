package com.app.videodownloaderapp.unknown2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MusicConsumptionInfo{

	@SerializedName("display_labels")
	private Object displayLabels;

	@SerializedName("should_allow_music_editing")
	private boolean shouldAllowMusicEditing;

	@SerializedName("should_mute_audio_reason")
	private String shouldMuteAudioReason;

	@SerializedName("overlap_duration_in_ms")
	private int overlapDurationInMs;

	@SerializedName("is_trending_in_clips")
	private boolean isTrendingInClips;

	@SerializedName("derived_content_id")
	private Object derivedContentId;

	@SerializedName("audio_asset_start_time_in_ms")
	private int audioAssetStartTimeInMs;

	@SerializedName("should_mute_audio")
	private boolean shouldMuteAudio;

	@SerializedName("audio_muting_info")
	private AudioMutingInfo audioMutingInfo;

	@SerializedName("audio_filter_infos")
	private List<Object> audioFilterInfos;

	@SerializedName("should_mute_audio_reason_type")
	private Object shouldMuteAudioReasonType;

	@SerializedName("ig_artist")
	private Object igArtist;

	@SerializedName("is_bookmarked")
	private boolean isBookmarked;

	@SerializedName("trend_rank")
	private Object trendRank;

	@SerializedName("allow_media_creation_with_music")
	private boolean allowMediaCreationWithMusic;

	@SerializedName("formatted_clips_media_count")
	private Object formattedClipsMediaCount;

	@SerializedName("placeholder_profile_pic_url")
	private String placeholderProfilePicUrl;

	public void setDisplayLabels(Object displayLabels){
		this.displayLabels = displayLabels;
	}

	public Object getDisplayLabels(){
		return displayLabels;
	}

	public void setShouldAllowMusicEditing(boolean shouldAllowMusicEditing){
		this.shouldAllowMusicEditing = shouldAllowMusicEditing;
	}

	public boolean isShouldAllowMusicEditing(){
		return shouldAllowMusicEditing;
	}

	public void setShouldMuteAudioReason(String shouldMuteAudioReason){
		this.shouldMuteAudioReason = shouldMuteAudioReason;
	}

	public String getShouldMuteAudioReason(){
		return shouldMuteAudioReason;
	}

	public void setOverlapDurationInMs(int overlapDurationInMs){
		this.overlapDurationInMs = overlapDurationInMs;
	}

	public int getOverlapDurationInMs(){
		return overlapDurationInMs;
	}

	public void setIsTrendingInClips(boolean isTrendingInClips){
		this.isTrendingInClips = isTrendingInClips;
	}

	public boolean isIsTrendingInClips(){
		return isTrendingInClips;
	}

	public void setDerivedContentId(Object derivedContentId){
		this.derivedContentId = derivedContentId;
	}

	public Object getDerivedContentId(){
		return derivedContentId;
	}

	public void setAudioAssetStartTimeInMs(int audioAssetStartTimeInMs){
		this.audioAssetStartTimeInMs = audioAssetStartTimeInMs;
	}

	public int getAudioAssetStartTimeInMs(){
		return audioAssetStartTimeInMs;
	}

	public void setShouldMuteAudio(boolean shouldMuteAudio){
		this.shouldMuteAudio = shouldMuteAudio;
	}

	public boolean isShouldMuteAudio(){
		return shouldMuteAudio;
	}

	public void setAudioMutingInfo(AudioMutingInfo audioMutingInfo){
		this.audioMutingInfo = audioMutingInfo;
	}

	public AudioMutingInfo getAudioMutingInfo(){
		return audioMutingInfo;
	}

	public void setAudioFilterInfos(List<Object> audioFilterInfos){
		this.audioFilterInfos = audioFilterInfos;
	}

	public List<Object> getAudioFilterInfos(){
		return audioFilterInfos;
	}

	public void setShouldMuteAudioReasonType(Object shouldMuteAudioReasonType){
		this.shouldMuteAudioReasonType = shouldMuteAudioReasonType;
	}

	public Object getShouldMuteAudioReasonType(){
		return shouldMuteAudioReasonType;
	}

	public void setIgArtist(Object igArtist){
		this.igArtist = igArtist;
	}

	public Object getIgArtist(){
		return igArtist;
	}

	public void setIsBookmarked(boolean isBookmarked){
		this.isBookmarked = isBookmarked;
	}

	public boolean isIsBookmarked(){
		return isBookmarked;
	}

	public void setTrendRank(Object trendRank){
		this.trendRank = trendRank;
	}

	public Object getTrendRank(){
		return trendRank;
	}

	public void setAllowMediaCreationWithMusic(boolean allowMediaCreationWithMusic){
		this.allowMediaCreationWithMusic = allowMediaCreationWithMusic;
	}

	public boolean isAllowMediaCreationWithMusic(){
		return allowMediaCreationWithMusic;
	}

	public void setFormattedClipsMediaCount(Object formattedClipsMediaCount){
		this.formattedClipsMediaCount = formattedClipsMediaCount;
	}

	public Object getFormattedClipsMediaCount(){
		return formattedClipsMediaCount;
	}

	public void setPlaceholderProfilePicUrl(String placeholderProfilePicUrl){
		this.placeholderProfilePicUrl = placeholderProfilePicUrl;
	}

	public String getPlaceholderProfilePicUrl(){
		return placeholderProfilePicUrl;
	}

	@Override
 	public String toString(){
		return 
			"MusicConsumptionInfo{" + 
			"display_labels = '" + displayLabels + '\'' + 
			",should_allow_music_editing = '" + shouldAllowMusicEditing + '\'' + 
			",should_mute_audio_reason = '" + shouldMuteAudioReason + '\'' + 
			",overlap_duration_in_ms = '" + overlapDurationInMs + '\'' + 
			",is_trending_in_clips = '" + isTrendingInClips + '\'' + 
			",derived_content_id = '" + derivedContentId + '\'' + 
			",audio_asset_start_time_in_ms = '" + audioAssetStartTimeInMs + '\'' + 
			",should_mute_audio = '" + shouldMuteAudio + '\'' + 
			",audio_muting_info = '" + audioMutingInfo + '\'' + 
			",audio_filter_infos = '" + audioFilterInfos + '\'' + 
			",should_mute_audio_reason_type = '" + shouldMuteAudioReasonType + '\'' + 
			",ig_artist = '" + igArtist + '\'' + 
			",is_bookmarked = '" + isBookmarked + '\'' + 
			",trend_rank = '" + trendRank + '\'' + 
			",allow_media_creation_with_music = '" + allowMediaCreationWithMusic + '\'' + 
			",formatted_clips_media_count = '" + formattedClipsMediaCount + '\'' + 
			",placeholder_profile_pic_url = '" + placeholderProfilePicUrl + '\'' + 
			"}";
		}
}