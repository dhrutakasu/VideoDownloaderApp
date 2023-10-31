package com.app.videodownloaderapp.unknown2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CarouselMediaItem{

	@SerializedName("original_width")
	private int originalWidth;

	@SerializedName("carousel_parent_id")
	private String carouselParentId;

	@SerializedName("preview")
	private String preview;

	@SerializedName("featured_products")
	private List<Object> featuredProducts;

	@SerializedName("shop_routing_user_id")
	private Object shopRoutingUserId;

	@SerializedName("usertags")
	private Usertags usertags;

	@SerializedName("commerciality_status")
	private String commercialityStatus;

	@SerializedName("product_suggestions")
	private List<Object> productSuggestions;

	@SerializedName("sharing_friction_info")
	private SharingFrictionInfo sharingFrictionInfo;

	@SerializedName("product_type")
	private String productType;

	@SerializedName("media_type")
	private int mediaType;

	@SerializedName("taken_at")
	private int takenAt;

	@SerializedName("explore_pivot_grid")
	private boolean explorePivotGrid;

	@SerializedName("image_versions2")
	private ImageVersions2 imageVersions2;

	@SerializedName("original_height")
	private int originalHeight;

	@SerializedName("id")
	private String id;

	@SerializedName("pk")
	private long pk;

	@SerializedName("fb_user_tags")
	private FbUserTags fbUserTags;

	@SerializedName("strong_id__")
	private String strongId;

	public void setOriginalWidth(int originalWidth){
		this.originalWidth = originalWidth;
	}

	public int getOriginalWidth(){
		return originalWidth;
	}

	public void setCarouselParentId(String carouselParentId){
		this.carouselParentId = carouselParentId;
	}

	public String getCarouselParentId(){
		return carouselParentId;
	}

	public void setPreview(String preview){
		this.preview = preview;
	}

	public String getPreview(){
		return preview;
	}

	public void setFeaturedProducts(List<Object> featuredProducts){
		this.featuredProducts = featuredProducts;
	}

	public List<Object> getFeaturedProducts(){
		return featuredProducts;
	}

	public void setShopRoutingUserId(Object shopRoutingUserId){
		this.shopRoutingUserId = shopRoutingUserId;
	}

	public Object getShopRoutingUserId(){
		return shopRoutingUserId;
	}

	public void setUsertags(Usertags usertags){
		this.usertags = usertags;
	}

	public Usertags getUsertags(){
		return usertags;
	}

	public void setCommercialityStatus(String commercialityStatus){
		this.commercialityStatus = commercialityStatus;
	}

	public String getCommercialityStatus(){
		return commercialityStatus;
	}

	public void setProductSuggestions(List<Object> productSuggestions){
		this.productSuggestions = productSuggestions;
	}

	public List<Object> getProductSuggestions(){
		return productSuggestions;
	}

	public void setSharingFrictionInfo(SharingFrictionInfo sharingFrictionInfo){
		this.sharingFrictionInfo = sharingFrictionInfo;
	}

	public SharingFrictionInfo getSharingFrictionInfo(){
		return sharingFrictionInfo;
	}

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return productType;
	}

	public void setMediaType(int mediaType){
		this.mediaType = mediaType;
	}

	public int getMediaType(){
		return mediaType;
	}

	public void setTakenAt(int takenAt){
		this.takenAt = takenAt;
	}

	public int getTakenAt(){
		return takenAt;
	}

	public void setExplorePivotGrid(boolean explorePivotGrid){
		this.explorePivotGrid = explorePivotGrid;
	}

	public boolean isExplorePivotGrid(){
		return explorePivotGrid;
	}

	public void setImageVersions2(ImageVersions2 imageVersions2){
		this.imageVersions2 = imageVersions2;
	}

	public ImageVersions2 getImageVersions2(){
		return imageVersions2;
	}

	public void setOriginalHeight(int originalHeight){
		this.originalHeight = originalHeight;
	}

	public int getOriginalHeight(){
		return originalHeight;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPk(long pk){
		this.pk = pk;
	}

	public long getPk(){
		return pk;
	}

	public void setFbUserTags(FbUserTags fbUserTags){
		this.fbUserTags = fbUserTags;
	}

	public FbUserTags getFbUserTags(){
		return fbUserTags;
	}

	public void setStrongId(String strongId){
		this.strongId = strongId;
	}

	public String getStrongId(){
		return strongId;
	}

	@Override
 	public String toString(){
		return 
			"CarouselMediaItem{" + 
			"original_width = '" + originalWidth + '\'' + 
			",carousel_parent_id = '" + carouselParentId + '\'' + 
			",preview = '" + preview + '\'' + 
			",featured_products = '" + featuredProducts + '\'' + 
			",shop_routing_user_id = '" + shopRoutingUserId + '\'' + 
			",usertags = '" + usertags + '\'' + 
			",commerciality_status = '" + commercialityStatus + '\'' + 
			",product_suggestions = '" + productSuggestions + '\'' + 
			",sharing_friction_info = '" + sharingFrictionInfo + '\'' + 
			",product_type = '" + productType + '\'' + 
			",media_type = '" + mediaType + '\'' + 
			",taken_at = '" + takenAt + '\'' + 
			",explore_pivot_grid = '" + explorePivotGrid + '\'' + 
			",image_versions2 = '" + imageVersions2 + '\'' + 
			",original_height = '" + originalHeight + '\'' + 
			",id = '" + id + '\'' + 
			",pk = '" + pk + '\'' + 
			",fb_user_tags = '" + fbUserTags + '\'' + 
			",strong_id__ = '" + strongId + '\'' + 
			"}";
		}
}