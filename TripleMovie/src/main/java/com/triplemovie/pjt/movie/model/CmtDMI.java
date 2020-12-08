package com.triplemovie.pjt.movie.model;

import java.util.List;

public class CmtDMI extends CmtVO{
	private String nick_nm;
	private String ctnt_favorite;
	private String ctnt_cnt;
	private int seq;
	private List<CmtDMI> cmt_info;
	private List<CmtVO> cmt_favorite;
	
	public List<CmtDMI> getCmt_info() {
		return cmt_info;
	}
	public void setCmt_info(List<CmtDMI> cmt_info) {
		this.cmt_info = cmt_info;
	}
	public List<CmtVO> getCmt_favorite() {
		return cmt_favorite;
	}
	public void setCmt_favorite(List<CmtVO> cmt_favorite) {
		this.cmt_favorite = cmt_favorite;
	}
	public String getNick_nm() {
		return nick_nm;
	}
	public void setNick_nm(String nick_nm) {
		this.nick_nm = nick_nm;
	}
	public String getCtnt_favorite() {
		return ctnt_favorite;
	}
	public void setCtnt_favorite(String ctnt_favorite) {
		this.ctnt_favorite = ctnt_favorite;
	}
	public String getCtnt_cnt() {
		return ctnt_cnt;
	}
	public void setCtnt_cnt(String ctnt_cnt) {
		this.ctnt_cnt = ctnt_cnt;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
