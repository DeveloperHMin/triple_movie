package com.triplemovie.pjt.api.model.kmdb;

import com.google.gson.annotations.SerializedName;

public class KmdbDTO {
	@SerializedName("Query")
	private String query;
	@SerializedName("KMAQuery")
    private String kmaQuery;
	@SerializedName("TotalCount")
    private long totalCount;
	@SerializedName("Data")
    private Datum[] data;
	private String videocode;
	private String movieId;
	private String movieSeq;
	
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieSeq() {
		return movieSeq;
	}
	public void setMovieSeq(String movieSeq) {
		this.movieSeq = movieSeq;
	}
	public String getVideocode() {
		return videocode;
	}
	public void setVideocode(String videocode) {
		this.videocode = videocode;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getKmaQuery() {
		return kmaQuery;
	}
	public void setKmaQuery(String kmaQuery) {
		this.kmaQuery = kmaQuery;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public Datum[] getData() {
		return data;
	}
	public void setData(Datum[] data) {
		this.data = data;
	}
    
}
