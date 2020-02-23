package me.abinthomas.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.abinthomas.moviecatalogservice.models.CatalogItem;
import me.abinthomas.moviecatalogservice.models.UserRating;
import me.abinthomas.moviecatalogservice.services.MovieInfo;
import me.abinthomas.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = userRatingInfo.fetchUserRating(userId);

        return userRating.getUserRating().stream()
            .map(rating -> movieInfo.getCatalogItem(rating))
            .collect(Collectors.toList());
    }
    
}