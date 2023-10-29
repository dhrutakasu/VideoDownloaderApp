package com.app.videodownloaderapp.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DpMakerModelItem{

	@SerializedName("path")
	private String path;

	@SerializedName("images")
	private List<String> images;

	@SerializedName("name")
	private String name;

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"DpMakerModelItem{" + 
			"path = '" + path + '\'' + 
			",images = '" + images + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}