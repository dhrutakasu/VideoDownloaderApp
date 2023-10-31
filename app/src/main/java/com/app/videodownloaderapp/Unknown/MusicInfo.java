package com.app.videodownloaderapp.Unknown;

import com.google.gson.annotations.SerializedName;

public class MusicInfo{

	@SerializedName("music_asset_info")
	private MusicAssetInfo musicAssetInfo;

	@SerializedName("music_consumption_info")
	private MusicConsumptionInfo musicConsumptionInfo;

	@SerializedName("music_canonical_id")
	private Object musicCanonicalId;

	public void setMusicAssetInfo(MusicAssetInfo musicAssetInfo){
		this.musicAssetInfo = musicAssetInfo;
	}

	public MusicAssetInfo getMusicAssetInfo(){
		return musicAssetInfo;
	}

	public void setMusicConsumptionInfo(MusicConsumptionInfo musicConsumptionInfo){
		this.musicConsumptionInfo = musicConsumptionInfo;
	}

	public MusicConsumptionInfo getMusicConsumptionInfo(){
		return musicConsumptionInfo;
	}

	public void setMusicCanonicalId(Object musicCanonicalId){
		this.musicCanonicalId = musicCanonicalId;
	}

	public Object getMusicCanonicalId(){
		return musicCanonicalId;
	}

	@Override
 	public String toString(){
		return 
			"MusicInfo{" + 
			"music_asset_info = '" + musicAssetInfo + '\'' + 
			",music_consumption_info = '" + musicConsumptionInfo + '\'' + 
			",music_canonical_id = '" + musicCanonicalId + '\'' + 
			"}";
		}
}