package com.triplemovie.pjt.api.model.kmdb;

import com.google.gson.annotations.SerializedName;

public class Rating {
	@SerializedName("ratingMain")
    private String ratingMain;
	@SerializedName("ratingDate")
    private String ratingDate;
	@SerializedName("ratingNo")
    private String ratingNo;
	@SerializedName("ratingGrade")
    private String ratingGrade;
	@SerializedName("releaseDate")
    private String releaseDate;
	@SerializedName("runtime")
    private String runtime;
	public String getRatingMain() {
		return ratingMain;
	}
	public void setRatingMain(String ratingMain) {
		this.ratingMain = ratingMain;
	}
	public String getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(String ratingDate) {
		this.ratingDate = ratingDate;
	}
	public String getRatingNo() {
		return ratingNo;
	}
	public void setRatingNo(String ratingNo) {
		this.ratingNo = ratingNo;
	}
	public String getRatingGrade() {
		return ratingGrade;
	}
	public void setRatingGrade(String ratingGrade) {
		this.ratingGrade = ratingGrade;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
    
    
}
