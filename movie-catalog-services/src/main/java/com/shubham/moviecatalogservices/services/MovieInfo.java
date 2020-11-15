package com.shubham.moviecatalogservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shubham.moviecatalogservices.models.CatalogItem;
import com.shubham.moviecatalogservices.models.Movie;
import com.shubham.moviecatalogservices.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackMyCatalog")
	public CatalogItem getMyCatalogs(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(),"Desc..",rating.getRating());
	}
	
	public CatalogItem getFallbackMyCatalog(Rating rating) {
		return new CatalogItem("...fallback","Desc..",0);
	}

}


