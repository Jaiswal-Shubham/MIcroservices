package com.shubham.moviecatalogservices.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shubham.moviecatalogservices.models.Rating;
import com.shubham.moviecatalogservices.models.UserRating;

@Service
public class RatingInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackRating"
//			,commandProperties = {
//					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
//					@HystrixProperty(name="CircuitBreaker.requestVolumeThreshold",value="5"),
//					@HystrixProperty(name="CircuitBreaker.errorThresholdPercentage",value="50"),
//					@HystrixProperty(name="CircuitBreaker.sleepWindowInMilliseconds",value="5000")
//			}
			)
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratings/users/"+userId, UserRating.class);
	}
	
	public UserRating getFallbackRating(String userId) {
		
		UserRating userRating = new UserRating();
		userRating.setUserRatings(Arrays.asList(new Rating(userId,0)));
		
		return userRating;
		
	}
}
