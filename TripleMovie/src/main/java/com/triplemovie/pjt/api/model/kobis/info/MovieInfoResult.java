package com.triplemovie.pjt.api.model.kobis.info;

//영화정보 결과
public class MovieInfoResult {
	private MovieInfo movieInfo;
    private String source;
    
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}
	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
    
    
}
