package com.triplemovie.pjt.api.model.kobis;

public class MovieListResult {
    private long totCnt;
    private String source;
    private MovieList[] movieList;
	public long getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(long totCnt) {
		this.totCnt = totCnt;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public MovieList[] getMovieList() {
		return movieList;
	}
	public void setMovieList(MovieList[] movieList) {
		this.movieList = movieList;
	}
    
    
}
