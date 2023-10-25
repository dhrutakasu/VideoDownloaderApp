package com.app.videodownloaderapp.Models;

import java.util.List;

public class DataItem{

	private String name;

	private int id;

	private Object status;

	private List<HashtagItem> hashtag;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(Object status){
		this.status = status;
	}

	public Object getStatus(){
		return status;
	}

	public void setHashtag(List<HashtagItem> hashtag){
		this.hashtag = hashtag;
	}

	public List<HashtagItem> getHashtag(){
		return hashtag;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			",hashtag = '" + hashtag + '\'' + 
			"}";
		}
}