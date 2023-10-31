package com.app.videodownloaderapp.Unknown;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MusicAssetInfo{

	@SerializedName("cover_artwork_thumbnail_uri")
	private String coverArtworkThumbnailUri;

	@SerializedName("display_artist")
	private String displayArtist;

	@SerializedName("dash_manifest")
	private Object dashManifest;

	@SerializedName("sanitized_title")
	private Object sanitizedTitle;

	@SerializedName("fast_start_progressive_download_url")
	private String fastStartProgressiveDownloadUrl;

	@SerializedName("audio_asset_id")
	private String audioAssetId;

	@SerializedName("cover_artwork_uri")
	private String coverArtworkUri;

	@SerializedName("dark_message")
	private Object darkMessage;

	@SerializedName("title")
	private String title;

	@SerializedName("audio_cluster_id")
	private String audioClusterId;

	@SerializedName("artist_id")
	private String artistId;

	@SerializedName("progressive_download_url")
	private String progressiveDownloadUrl;

	@SerializedName("allows_saving")
	private boolean allowsSaving;

	@SerializedName("reactive_audio_download_url")
	private Object reactiveAudioDownloadUrl;

	@SerializedName("subtitle")
	private String subtitle;

	@SerializedName("web_30s_preview_download_url")
	private String web30sPreviewDownloadUrl;

	@SerializedName("is_eligible_for_audio_effects")
	private boolean isEligibleForAudioEffects;

	@SerializedName("id")
	private String id;

	@SerializedName("has_lyrics")
	private boolean hasLyrics;

	@SerializedName("highlight_start_times_in_ms")
	private List<Integer> highlightStartTimesInMs;

	@SerializedName("is_explicit")
	private boolean isExplicit;

	@SerializedName("duration_in_ms")
	private int durationInMs;

	@SerializedName("ig_username")
	private String igUsername;

	public void setCoverArtworkThumbnailUri(String coverArtworkThumbnailUri){
		this.coverArtworkThumbnailUri = coverArtworkThumbnailUri;
	}

	public String getCoverArtworkThumbnailUri(){
		return coverArtworkThumbnailUri;
	}

	public void setDisplayArtist(String displayArtist){
		this.displayArtist = displayArtist;
	}

	public String getDisplayArtist(){
		return displayArtist;
	}

	public void setDashManifest(Object dashManifest){
		this.dashManifest = dashManifest;
	}

	public Object getDashManifest(){
		return dashManifest;
	}

	public void setSanitizedTitle(Object sanitizedTitle){
		this.sanitizedTitle = sanitizedTitle;
	}

	public Object getSanitizedTitle(){
		return sanitizedTitle;
	}

	public void setFastStartProgressiveDownloadUrl(String fastStartProgressiveDownloadUrl){
		this.fastStartProgressiveDownloadUrl = fastStartProgressiveDownloadUrl;
	}

	public String getFastStartProgressiveDownloadUrl(){
		return fastStartProgressiveDownloadUrl;
	}

	public void setAudioAssetId(String audioAssetId){
		this.audioAssetId = audioAssetId;
	}

	public String getAudioAssetId(){
		return audioAssetId;
	}

	public void setCoverArtworkUri(String coverArtworkUri){
		this.coverArtworkUri = coverArtworkUri;
	}

	public String getCoverArtworkUri(){
		return coverArtworkUri;
	}

	public void setDarkMessage(Object darkMessage){
		this.darkMessage = darkMessage;
	}

	public Object getDarkMessage(){
		return darkMessage;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setAudioClusterId(String audioClusterId){
		this.audioClusterId = audioClusterId;
	}

	public String getAudioClusterId(){
		return audioClusterId;
	}

	public void setArtistId(String artistId){
		this.artistId = artistId;
	}

	public String getArtistId(){
		return artistId;
	}

	public void setProgressiveDownloadUrl(String progressiveDownloadUrl){
		this.progressiveDownloadUrl = progressiveDownloadUrl;
	}

	public String getProgressiveDownloadUrl(){
		return progressiveDownloadUrl;
	}

	public void setAllowsSaving(boolean allowsSaving){
		this.allowsSaving = allowsSaving;
	}

	public boolean isAllowsSaving(){
		return allowsSaving;
	}

	public void setReactiveAudioDownloadUrl(Object reactiveAudioDownloadUrl){
		this.reactiveAudioDownloadUrl = reactiveAudioDownloadUrl;
	}

	public Object getReactiveAudioDownloadUrl(){
		return reactiveAudioDownloadUrl;
	}

	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}

	public String getSubtitle(){
		return subtitle;
	}

	public void setWeb30sPreviewDownloadUrl(String web30sPreviewDownloadUrl){
		this.web30sPreviewDownloadUrl = web30sPreviewDownloadUrl;
	}

	public String getWeb30sPreviewDownloadUrl(){
		return web30sPreviewDownloadUrl;
	}

	public void setIsEligibleForAudioEffects(boolean isEligibleForAudioEffects){
		this.isEligibleForAudioEffects = isEligibleForAudioEffects;
	}

	public boolean isIsEligibleForAudioEffects(){
		return isEligibleForAudioEffects;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setHasLyrics(boolean hasLyrics){
		this.hasLyrics = hasLyrics;
	}

	public boolean isHasLyrics(){
		return hasLyrics;
	}

	public void setHighlightStartTimesInMs(List<Integer> highlightStartTimesInMs){
		this.highlightStartTimesInMs = highlightStartTimesInMs;
	}

	public List<Integer> getHighlightStartTimesInMs(){
		return highlightStartTimesInMs;
	}

	public void setIsExplicit(boolean isExplicit){
		this.isExplicit = isExplicit;
	}

	public boolean isIsExplicit(){
		return isExplicit;
	}

	public void setDurationInMs(int durationInMs){
		this.durationInMs = durationInMs;
	}

	public int getDurationInMs(){
		return durationInMs;
	}

	public void setIgUsername(String igUsername){
		this.igUsername = igUsername;
	}

	public String getIgUsername(){
		return igUsername;
	}

	@Override
 	public String toString(){
		return 
			"MusicAssetInfo{" + 
			"cover_artwork_thumbnail_uri = '" + coverArtworkThumbnailUri + '\'' + 
			",display_artist = '" + displayArtist + '\'' + 
			",dash_manifest = '" + dashManifest + '\'' + 
			",sanitized_title = '" + sanitizedTitle + '\'' + 
			",fast_start_progressive_download_url = '" + fastStartProgressiveDownloadUrl + '\'' + 
			",audio_asset_id = '" + audioAssetId + '\'' + 
			",cover_artwork_uri = '" + coverArtworkUri + '\'' + 
			",dark_message = '" + darkMessage + '\'' + 
			",title = '" + title + '\'' + 
			",audio_cluster_id = '" + audioClusterId + '\'' + 
			",artist_id = '" + artistId + '\'' + 
			",progressive_download_url = '" + progressiveDownloadUrl + '\'' + 
			",allows_saving = '" + allowsSaving + '\'' + 
			",reactive_audio_download_url = '" + reactiveAudioDownloadUrl + '\'' + 
			",subtitle = '" + subtitle + '\'' + 
			",web_30s_preview_download_url = '" + web30sPreviewDownloadUrl + '\'' + 
			",is_eligible_for_audio_effects = '" + isEligibleForAudioEffects + '\'' + 
			",id = '" + id + '\'' + 
			",has_lyrics = '" + hasLyrics + '\'' + 
			",highlight_start_times_in_ms = '" + highlightStartTimesInMs + '\'' + 
			",is_explicit = '" + isExplicit + '\'' + 
			",duration_in_ms = '" + durationInMs + '\'' + 
			",ig_username = '" + igUsername + '\'' + 
			"}";
		}
}