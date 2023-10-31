package com.app.videodownloaderapp.Unknown;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MusicMetadata{

	@SerializedName("music_info")
	private MusicInfo musicInfo;

	@SerializedName("music_canonical_id")
	private String musicCanonicalId;

	@SerializedName("original_sound_info")
	private Object originalSoundInfo;

	@SerializedName("pinned_media_ids")
	private List<Object> pinnedMediaIds;

	@SerializedName("audio_type")
	private String audioType;

	public void setMusicInfo(MusicInfo musicInfo){
		this.musicInfo = musicInfo;
	}

	public MusicInfo getMusicInfo(){
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

	public void setPinnedMediaIds(List<Object> pinnedMediaIds){
		this.pinnedMediaIds = pinnedMediaIds;
	}

	public List<Object> getPinnedMediaIds(){
		return pinnedMediaIds;
	}

	public void setAudioType(String audioType){
		this.audioType = audioType;
	}

	public String getAudioType(){
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