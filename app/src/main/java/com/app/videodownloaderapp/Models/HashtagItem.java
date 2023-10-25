package com.app.videodownloaderapp.Models;

public class HashtagItem{

	private Object updatedAt;

	private String tagName;

	private String tagNameDetail;

	private Object createdAt;

	private int id;

	private String category;

	private Object deletedAt;

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setTagName(String tagName){
		this.tagName = tagName;
	}

	public String getTagName(){
		return tagName;
	}

	public void setTagNameDetail(String tagNameDetail){
		this.tagNameDetail = tagNameDetail;
	}

	public String getTagNameDetail(){
		return tagNameDetail;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	@Override
 	public String toString(){
		return 
			"HashtagItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",tag_name = '" + tagName + '\'' + 
			",tag_name_detail = '" + tagNameDetail + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			"}";
		}
}