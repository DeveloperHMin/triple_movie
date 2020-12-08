package com.triplemovie.pjt.user.model;

public class UserTicketVO {
	private String movieNm;
	private String movieSeq;
	private String movieId;
	private int i_movie;
	private int i_user;
	private String r_dt;
	private int room;
	private int s_dt;//분으로 담겨있음
	private int e_dt;
	private String[] seats;
	private String seat;
	private int seatCnt; //몇개 예매했는지
	private int i_ticket;//티켓테이블 pk
	private String s_Time;
	private String e_Time;
	private String[] rows;
	private int adultCnt;
	private int studentCnt;

	
	public int getAdultCnt() {
		return adultCnt;
	}
	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}
	public int getStudentCnt() {
		return studentCnt;
	}
	public void setStudentCnt(int studentCnt) {
		this.studentCnt = studentCnt;
	}
	public int getI_movie() {
		return i_movie;
	}
	public void setI_movie(int i_movie) {
		this.i_movie = i_movie;
	}
	public int getE_dt() {
		return e_dt;
	}
	public void setE_dt(int e_dt) {
		this.e_dt = e_dt;
	}
	public String getE_Time() {
		return e_Time;
	}
	public void setE_Time(String e_Time) {
		this.e_Time = e_Time;
	}
	public String[] getRows() {
		return rows;
	}
	public void setRows(String[] rows) {
		this.rows = rows;
	}
	public String getS_Time() {
		return s_Time;
	}
	public void setS_Time(String s_Time) {
		this.s_Time = s_Time;
	}

	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public int getI_ticket() {
		return i_ticket;
	}
	public void setI_ticket(int i_ticket) {
		this.i_ticket = i_ticket;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
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
	public String[] getSeats() {
		return seats;
	}
	public void setSeats(String[] seats) {
		this.seats = seats;
	}
	public int getSeatCnt() {
		return seatCnt;
	}
	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
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
	
	
	
}
