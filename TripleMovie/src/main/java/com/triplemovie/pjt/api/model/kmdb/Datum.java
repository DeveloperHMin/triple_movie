package com.triplemovie.pjt.api.model.kmdb;

import com.google.gson.annotations.SerializedName;

public class Datum {
	@SerializedName("CollName")
	private String collName;
	@SerializedName("TotalCount")
    private long totalCount;
	@SerializedName("Count")
    private long count;
	@SerializedName("Result")
    private Result[] result;
	public String getCollName() {
		return collName;
	}
	public void setCollName(String collName) {
		this.collName = collName;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Result[] getResult() {
		return result;
	}
	public void setResult(Result[] result) {
		this.result = result;
	}
	
	
    
    
}
