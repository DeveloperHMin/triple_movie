package com.triplemovie.pjt.movie.model;

public class CinemaVO {
	private int i_movie;
	private String movieNm;
	private String movieSeq;
	private String movieId;
	private int room;
	private int s_dt;
	private int e_dt;
	private int r_dt;
	private String sTime;
	private String eTime;
	
	
	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	public String getMovieSeq() {
		return movieSeq;
	}
	public void setMovieSeq(String movieSeq) {
		this.movieSeq = movieSeq;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getR_dt() {
		return r_dt;
	}
	public void setR_dt(int r_dt) {
		this.r_dt = r_dt;
	}
	public int getI_movie() {
		return i_movie;
	}
	public void setI_movie(int i_movie) {
		this.i_movie = i_movie;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getS_dt() {
		return s_dt;
	}
	public void setS_dt(int s_dt) {
		this.s_dt = s_dt;
	}
	public int getE_dt() {
		return e_dt;
	}
	public void setE_dt(int e_dt) {
		this.e_dt = e_dt;
	}
}
