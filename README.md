This is a maven spring boot project in which three services(Eureka Client) namely rating service, movie-catalog service and movie-info service talks to each other through another load balancer server namely discover service. Here discovery-service are one of the Eureka server which is provided by the netflix-spring api's.  
///////////////////////////////////////////////////////////////////////////////

Steps to run this project
1)Import all the 4 maven project in your IDE(i used Eclipse), and update maven to get all the required jar for each project.
2)Run DiscoveryServerApplication.java file to run Discovery service first, so that Eureka server can start listening to it's clients.
3)Next, we have to run other three services accordingly.
//////////////////////////////////////////////////////////////////////////////

Api-details:
#Movie-Info service:
  http://localhost:8082/movies/{movieId}
  above api will fetch movie details from https://api.themoviedb.org/3/movie/movieId/?api_key="...."
  using restTemplate and will implicitly convert return object into json.
  
#Rating service:
  http://localhost:8083/ratings/users/{userId}
  above api will fetch user's rating from userId
  
  #Movie-Catalog service
  http://localhost:8081/catalog/{userId}
  This is one of the discovery client provided by the spring.cloud whch call rating-info service to get ratings from the userId  and then for each rating data, it gets movie id an then again call movie-info service to get movie information from the movieId.
  
  @Hystrix is used for Circuit breaker
  @LoadBalanced restTemplate is used.
  @Autowired DiscoveryClient to call other services as generic web request..
