package com.shubham.ratingsdataservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.ratingsdataservices.models.Rating;
import com.shubham.ratingsdataservices.models.UserRating;

@RestController
@RequestMapping("ratings")
public class RatingController {
	
	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId,5);
	}
	
	@GetMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable String userId)
	{
		List<Rating> ratings =  Arrays.asList(
				new Rating("550",2),
				new Rating("560",5)
				);
		
		UserRating ur = new UserRating();
		ur.setUserRatings(ratings);
		return ur;
		
	}
}
