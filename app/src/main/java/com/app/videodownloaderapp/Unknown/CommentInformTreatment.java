package com.app.videodownloaderapp.Unknown;

import com.google.gson.annotations.SerializedName;

public class CommentInformTreatment{

	@SerializedName("action_type")
	private Object actionType;

	@SerializedName("text")
	private String text;

	@SerializedName("url")
	private Object url;

	@SerializedName("should_have_inform_treatment")
	private boolean shouldHaveInformTreatment;

	public void setActionType(Object actionType){
		this.actionType = actionType;
	}

	public Object getActionType(){
		return actionType;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUrl(Object url){
		this.url = url;
	}

	public Object getUrl(){
		return url;
	}

	public void setShouldHaveInformTreatment(boolean shouldHaveInformTreatment){
		this.shouldHaveInformTreatment = shouldHaveInformTreatment;
	}

	public boolean isShouldHaveInformTreatment(){
		return shouldHaveInformTreatment;
	}

	@Override
 	public String toString(){
		return 
			"CommentInformTreatment{" + 
			"action_type = '" + actionType + '\'' + 
			",text = '" + text + '\'' + 
			",url = '" + url + '\'' + 
			",should_have_inform_treatment = '" + shouldHaveInformTreatment + '\'' + 
			"}";
		}
}