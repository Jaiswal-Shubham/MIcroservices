package com.shubham.moviecatalogservices.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shubham.moviecatalogservices.models.CatalogItem;
import com.shubham.moviecatalogservices.models.Movie;
import com.shubham.moviecatalogservices.models.Rating;
import com.shubham.moviecatalogservices.models.UserRating;
import com.shubham.moviecatalogservices.services.MovieInfo;
import com.shubham.moviecatalogservices.services.RatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogServiceController {
	
	@Autowired
	private RatingInfo ratingInfo;
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	//WebClient
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId)
	{
		UserRating ratings = ratingInfo.getUserRating(userId);
		
		return ratings.getUserRatings().stream().map(rating->{
			return movieInfo.getMyCatalogs(rating);
		}).collect(Collectors.toList());
		
		//Movie movie1 = restTemplate.getForObject("http://localhost:8082/movies/1", Movie.class);
		
		//below code to perform api calls using web client
		//Movie movie1 = webClientBuilder.build().get().uri("http://localhost:8082/movies/1")
		//.retrieve().bodyToMono(Movie.class).block();
//		
//		System.out.println("Movie1 "+movie1);
//		List<Rating> ratings = Arrays.asList(
//								new Rating("1",4),
//								new Rating("2",5)
//								);
//		List<CatalogItem> lists =ratings.stream().map(rating ->{
//			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
//			return new CatalogItem(movie.getName(),"Desc..",rating.getRating());
//		})
//		.collect(Collectors.toList());
//		System.out.println("list is "+lists);
//		return lists;
	}
	

}
