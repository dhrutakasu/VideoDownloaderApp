package com.app.videodownloaderapp.Unknown;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MediaNotes{

	@SerializedName("items")
	private List<Object> items;

	public void setItems(List<Object> items){
		this.items = items;
	}

	public List<Object> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"MediaNotes{" + 
			"items = '" + items + '\'' + 
			"}";
		}
}