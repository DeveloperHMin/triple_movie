package com.triplemovie.pjt.api.model.kobis.info;
//영화정보
public class MovieInfo {
	// 이거나중에 지우든가
	private String movieCD;
    private String movieNm;
    private String movieNmEn;
    private String movieNmOg;
    private String showTm;
    private String prdtYear;
    private String openDt;
    private String prdtStatNm;
    private String typeNm;
    private Director[] directors;
    private Actor[] actors;
    private Audit[] audits;
    private Genre[] genres;
    
    
	
	public Genre[] getGenres() {
		return genres;
	}
	public void setGenres(Genre[] genres) {
		this.genres = genres;
	}
	public String getMovieCD() {
		return movieCD;
	}
	public void setMovieCD(String movieCD) {
		this.movieCD = movieCD;
	}
	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public String getMovieNmEn() {
		return movieNmEn;
	}
	public void setMovieNmEn(String movieNmEn) {
		this.movieNmEn = movieNmEn;
	}
	public String getMovieNmOg() {
		return movieNmOg;
	}
	public void setMovieNmOg(String movieNmOg) {
		this.movieNmOg = movieNmOg;
	}
	public String getShowTm() {
		return showTm;
	}
	public void setShowTm(String showTm) {
		this.showTm = showTm;
	}
	public String getPrdtYear() {
		return prdtYear;
	}
	public void setPrdtYear(String prdtYear) {
		this.prdtYear = prdtYear;
	}
	public String getOpenDt() {
		return openDt;
	}
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
	public String getPrdtStatNm() {
		return prdtStatNm;
	}
	public void setPrdtStatNm(String prdtStatNm) {
		this.prdtStatNm = prdtStatNm;
	}
	public String getTypeNm() {
		return typeNm;
	}
	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}
	public Director[] getDirectors() {
		return directors;
	}
	public void setDirectors(Director[] directors) {
		this.directors = directors;
	}
	public Actor[] getActors() {
		return actors;
	}
	public void setActors(Actor[] actors) {
		this.actors = actors;
	}
	public Audit[] getAudits() {
		return audits;
	}
	public void setAudits(Audit[] audits) {
		this.audits = audits;
	}
    
    
}
