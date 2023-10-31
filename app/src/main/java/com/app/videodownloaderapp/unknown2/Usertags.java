package com.app.videodownloaderapp.unknown2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Usertags{

	@SerializedName("in")
	private List<InItem> in;

	public void setIn(List<InItem> in){
		this.in = in;
	}

	public List<InItem> getIn(){
		return in;
	}

	@Override
 	public String toString(){
		return 
			"Usertags{" + 
			"in = '" + in + '\'' + 
			"}";
		}
}