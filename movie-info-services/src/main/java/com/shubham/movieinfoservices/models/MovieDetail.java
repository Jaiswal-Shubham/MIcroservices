package com.shubham.movieinfoservices.models;

public class MovieDetail {
	private String title;
	private String overview;
	
	public MovieDetail() {}
	
	public MovieDetail(String title, String overview) {
		this.title = title;
		this.overview = overview;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	

}
