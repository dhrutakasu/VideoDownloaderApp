package com.app.videodownloaderapp.Unknown;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FbUserTags{

	@SerializedName("in")
	private List<Object> in;

	public void setIn(List<Object> in){
		this.in = in;
	}

	public List<Object> getIn(){
		return in;
	}

	@Override
 	public String toString(){
		return 
			"FbUserTags{" + 
			"in = '" + in + '\'' + 
			"}";
		}
}