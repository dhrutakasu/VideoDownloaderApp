package com.app.videodownloaderapp.unknown2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("more_available")
	private boolean moreAvailable;

	@SerializedName("auto_load_more_enabled")
	private boolean autoLoadMoreEnabled;

	@SerializedName("items")
	private List<ItemsItem> items;

	@SerializedName("num_results")
	private int numResults;

	@SerializedName("showQRModal")
	private boolean showQRModal;

	public void setMoreAvailable(boolean moreAvailable){
		this.moreAvailable = moreAvailable;
	}

	public boolean isMoreAvailable(){
		return moreAvailable;
	}

	public void setAutoLoadMoreEnabled(boolean autoLoadMoreEnabled){
		this.autoLoadMoreEnabled = autoLoadMoreEnabled;
	}

	public boolean isAutoLoadMoreEnabled(){
		return autoLoadMoreEnabled;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	public void setNumResults(int numResults){
		this.numResults = numResults;
	}

	public int getNumResults(){
		return numResults;
	}

	public void setShowQRModal(boolean showQRModal){
		this.showQRModal = showQRModal;
	}

	public boolean isShowQRModal(){
		return showQRModal;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"more_available = '" + moreAvailable + '\'' + 
			",auto_load_more_enabled = '" + autoLoadMoreEnabled + '\'' + 
			",items = '" + items + '\'' + 
			",num_results = '" + numResults + '\'' + 
			",showQRModal = '" + showQRModal + '\'' + 
			"}";
		}
}