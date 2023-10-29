package com.app.videodownloaderapp.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DpMakerModel{

	@SerializedName("DpMakerModel")
	private List<DpMakerModelItem> dpMakerModel;

	public void setDpMakerModel(List<DpMakerModelItem> dpMakerModel){
		this.dpMakerModel = dpMakerModel;
	}

	public List<DpMakerModelItem> getDpMakerModel(){
		return dpMakerModel;
	}

	@Override
 	public String toString(){
		return 
			"DpMakerModel{" + 
			"dpMakerModel = '" + dpMakerModel + '\'' + 
			"}";
		}
}