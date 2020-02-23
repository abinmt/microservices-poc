package me.abinthomas.moviecatalogservice.services;

import java.util.Arrays;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import me.abinthomas.moviecatalogservice.models.Rating;
import me.abinthomas.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fetchFallbackUserRating" )
	public UserRating fetchUserRating(String userId) {
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
        return userRating;
    }

    public UserRating fetchFallbackUserRating(String userId) {
        UserRating userRating = new UserRating();

        userRating.setUserId(userId);

        userRating.setUserRating(Arrays.asList(
            new Rating("0", 0)
        ));

        return userRating;
    }
}
