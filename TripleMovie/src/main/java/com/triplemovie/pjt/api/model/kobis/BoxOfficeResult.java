package com.triplemovie.pjt.api.model.kobis;

import java.time.LocalDate;

public class BoxOfficeResult {
	
	private String boxofficeType;
    private String showRange;
    private DailyBoxOfficeList[] dailyBoxOfficeList;
    
	public String getBoxofficeType() {
		return boxofficeType;
	}
	public void setBoxofficeType(String boxofficeType) {
		this.boxofficeType = boxofficeType;
	}
	public String getShowRange() {
		return showRange;
	}
	public void setShowRange(String showRange) {
		this.showRange = showRange;
	}
	public DailyBoxOfficeList[] getDailyBoxOfficeList() {
		return dailyBoxOfficeList;
	}
	public void setDailyBoxOfficeList(DailyBoxOfficeList[] dailyBoxOfficeList) {
		this.dailyBoxOfficeList = dailyBoxOfficeList;
	}
    
    
  
		
}
