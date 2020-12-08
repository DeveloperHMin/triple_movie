package com.triplemovie.pjt.api.model.kobis.info;

//배우
public class Actor {
	private String peopleNm; //이름
    private String peopleNmEn;
    private String cast; //역할
    private String castEn;
	public String getPeopleNm() {
		return peopleNm;
	}
	public void setPeopleNm(String peopleNm) {
		this.peopleNm = peopleNm;
	}
	public String getPeopleNmEn() {
		return peopleNmEn;
	}
	public void setPeopleNmEn(String peopleNmEn) {
		this.peopleNmEn = peopleNmEn;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getCastEn() {
		return castEn;
	}
	public void setCastEn(String castEn) {
		this.castEn = castEn;
	}
    
    
}
