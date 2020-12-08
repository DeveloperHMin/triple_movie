package com.triplemovie.pjt.api.model.youtube;

public class Thumbnails {
    private Default thumbnailsDefault;
    private Default medium;
    public Default getThumbnailsDefault() {
		return thumbnailsDefault;
	}
	public void setThumbnailsDefault(Default thumbnailsDefault) {
		this.thumbnailsDefault = thumbnailsDefault;
	}
	public Default getMedium() {
		return medium;
	}
	public void setMedium(Default medium) {
		this.medium = medium;
	}
	public Default getHigh() {
		return high;
	}
	public void setHigh(Default high) {
		this.high = high;
	}
	private Default high;
}
