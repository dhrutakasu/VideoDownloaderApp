package com.app.videodownloaderapp.Unknown;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ImageVersions2{

	@SerializedName("candidates")
	private List<CandidatesItem> candidates;

	public void setCandidates(List<CandidatesItem> candidates){
		this.candidates = candidates;
	}

	public List<CandidatesItem> getCandidates(){
		return candidates;
	}

	@Override
 	public String toString(){
		return 
			"ImageVersions2{" + 
			"candidates = '" + candidates + '\'' + 
			"}";
		}
}