package com.shubham.moviecatalogservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServicesApplication {

	@Bean
	public WebClient.Builder getWebClientBuilder()
	{
		return WebClient.builder();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplateThroughBeans()
	{
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(3000);
		//return new RestTemplate(clientHttpRequestFactory);
		
		// not using timeout, as circuit breaker will take care of it
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServicesApplication.class, args);
	}

}
