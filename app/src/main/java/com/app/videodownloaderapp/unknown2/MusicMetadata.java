package com.app.videodownloaderapp.unknown2;

import com.google.gson.annotations.SerializedName;

public class MusicMetadata{

	@SerializedName("music_info")
	private Object musicInfo;

	@SerializedName("music_canonical_id")
	private String musicCanonicalId;

	@SerializedName("original_sound_info")
	private Object originalSoundInfo;

	@SerializedName("pinned_media_ids")
	private Object pinnedMediaIds;

	@SerializedName("audio_type")
	private Object audioType;

	public void setMusicInfo(Object musicInfo){
		this.musicInfo = musicInfo;
	}

	public Object getMusicInfo(){
		return musicInfo;
	}

	public void setMusicCanonicalId(String musicCanonicalId){
		this.musicCanonicalId = musicCanonicalId;
	}

	public String getMusicCanonicalId(){
		return musicCanonicalId;
	}

	public void setOriginalSoundInfo(Object originalSoundInfo){
		this.originalSoundInfo = originalSoundInfo;
	}

	public Object getOriginalSoundInfo(){
		return originalSoundInfo;
	}

	public void setPinnedMediaIds(Object pinnedMediaIds){
		this.pinnedMediaIds = pinnedMediaIds;
	}

	public Object getPinnedMediaIds(){
		return pinnedMediaIds;
	}

	public void setAudioType(Object audioType){
		this.audioType = audioType;
	}

	public Object getAudioType(){
		return audioType;
	}

	@Override
 	public String toString(){
		return 
			"MusicMetadata{" + 
			"music_info = '" + musicInfo + '\'' + 
			",music_canonical_id = '" + musicCanonicalId + '\'' + 
			",original_sound_info = '" + originalSoundInfo + '\'' + 
			",pinned_media_ids = '" + pinnedMediaIds + '\'' + 
			",audio_type = '" + audioType + '\'' + 
			"}";
		}
}