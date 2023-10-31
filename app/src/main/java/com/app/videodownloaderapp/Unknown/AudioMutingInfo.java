package com.app.videodownloaderapp.Unknown;

import com.google.gson.annotations.SerializedName;

public class AudioMutingInfo{

	@SerializedName("mute_audio")
	private boolean muteAudio;

	@SerializedName("mute_reason")
	private Object muteReason;

	@SerializedName("allow_audio_editing")
	private boolean allowAudioEditing;

	@SerializedName("mute_reason_str")
	private String muteReasonStr;

	@SerializedName("show_muted_audio_toast")
	private boolean showMutedAudioToast;

	public void setMuteAudio(boolean muteAudio){
		this.muteAudio = muteAudio;
	}

	public boolean isMuteAudio(){
		return muteAudio;
	}

	public void setMuteReason(Object muteReason){
		this.muteReason = muteReason;
	}

	public Object getMuteReason(){
		return muteReason;
	}

	public void setAllowAudioEditing(boolean allowAudioEditing){
		this.allowAudioEditing = allowAudioEditing;
	}

	public boolean isAllowAudioEditing(){
		return allowAudioEditing;
	}

	public void setMuteReasonStr(String muteReasonStr){
		this.muteReasonStr = muteReasonStr;
	}

	public String getMuteReasonStr(){
		return muteReasonStr;
	}

	public void setShowMutedAudioToast(boolean showMutedAudioToast){
		this.showMutedAudioToast = showMutedAudioToast;
	}

	public boolean isShowMutedAudioToast(){
		return showMutedAudioToast;
	}

	@Override
 	public String toString(){
		return 
			"AudioMutingInfo{" + 
			"mute_audio = '" + muteAudio + '\'' + 
			",mute_reason = '" + muteReason + '\'' + 
			",allow_audio_editing = '" + allowAudioEditing + '\'' + 
			",mute_reason_str = '" + muteReasonStr + '\'' + 
			",show_muted_audio_toast = '" + showMutedAudioToast + '\'' + 
			"}";
		}
}