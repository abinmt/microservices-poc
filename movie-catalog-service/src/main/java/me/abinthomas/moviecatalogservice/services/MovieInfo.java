package me.abinthomas.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import me.abinthomas.moviecatalogservice.models.CatalogItem;
import me.abinthomas.moviecatalogservice.models.Movie;
import me.abinthomas.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fetchFallbackCatalogItem" )
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

        return new CatalogItem(movie.getName(), "Nice", rating.getRating());
    }

    public CatalogItem fetchFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie not found", "", rating.getRating());
    }
}
