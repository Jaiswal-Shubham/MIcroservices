package com.shubham.movieinfoservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shubham.movieinfoservices.models.Movie;
import com.shubham.movieinfoservices.models.MovieDetail;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String  movieId)
	{
		String url = "https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey;
		System.out.println("url: "+ url);
		//MovieDetail movieDetail = new MovieDetail("MyTitle","MyOverview");
		MovieDetail movieDetail = restTemplate.getForObject(url, MovieDetail.class); 
		
		
		return new Movie(movieId,movieDetail.getTitle());
	}
	
}
