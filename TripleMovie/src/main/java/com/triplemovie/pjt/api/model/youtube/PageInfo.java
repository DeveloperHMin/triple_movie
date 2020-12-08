package com.triplemovie.pjt.api.model.youtube;

public class PageInfo {
    private long totalResults;
    public long getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}
	public long getResultsPerPage() {
		return resultsPerPage;
	}
	public void setResultsPerPage(long resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	private long resultsPerPage;
}
