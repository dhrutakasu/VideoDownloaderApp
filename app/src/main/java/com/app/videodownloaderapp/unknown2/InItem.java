package com.app.videodownloaderapp.unknown2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InItem{

	@SerializedName("position")
	private List<Object> position;

	@SerializedName("start_time_in_video_in_sec")
	private Object startTimeInVideoInSec;

	@SerializedName("user")
	private User user;

	@SerializedName("duration_in_video_in_sec")
	private Object durationInVideoInSec;

	public void setPosition(List<Object> position){
		this.position = position;
	}

	public List<Object> getPosition(){
		return position;
	}

	public void setStartTimeInVideoInSec(Object startTimeInVideoInSec){
		this.startTimeInVideoInSec = startTimeInVideoInSec;
	}

	public Object getStartTimeInVideoInSec(){
		return startTimeInVideoInSec;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setDurationInVideoInSec(Object durationInVideoInSec){
		this.durationInVideoInSec = durationInVideoInSec;
	}

	public Object getDurationInVideoInSec(){
		return durationInVideoInSec;
	}

	@Override
 	public String toString(){
		return 
			"InItem{" + 
			"position = '" + position + '\'' + 
			",start_time_in_video_in_sec = '" + startTimeInVideoInSec + '\'' + 
			",user = '" + user + '\'' + 
			",duration_in_video_in_sec = '" + durationInVideoInSec + '\'' + 
			"}";
		}
}