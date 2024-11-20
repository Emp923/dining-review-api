package com.codecademy.dining.controller;

import com.codecademy.dining.model.DiningUser;
import com.codecademy.dining.model.Restaurant;
import com.codecademy.dining.model.Review;
import com.codecademy.dining.repository.DiningUserRepository;
import com.codecademy.dining.repository.RestaurantRepository;
import com.codecademy.dining.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dining/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final DiningUserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewController(
            ReviewRepository reviewRepository,
            DiningUserRepository userRepository,
            RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public Iterable<Review> findAllReviews() {
        return this.reviewRepository.findAll();
    }

    @RequestMapping(path="/", method=RequestMethod.POST)
    public Review createReview(@RequestBody Review newReview) {
        Optional<DiningUser> optionalUser = this.userRepository.findById(newReview.getDiningUserId());
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(newReview.getRestaurantId());
        if (optionalUser.isEmpty() || optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dining user or restaurant does not exist");
        }
        Restaurant restaurant = optionalRestaurant.get();
        int restaurantReviewTotal = restaurant.getTotalReviews() + 1;
        restaurant.setTotalReviews(restaurantReviewTotal);

        this.restaurantRepository.save(restaurant);

        return this.reviewRepository.save(newReview);
    }

    @RequestMapping(path="/search", method=RequestMethod.GET)
    public Iterable<Review> searchReviews(@RequestParam("restaurantId") long restaurantId) {
        return this.reviewRepository.findByRestaurantId(restaurantId);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Review findReviewById(@PathVariable("id") long id) {
        Optional<Review> optionalReview = this.reviewRepository.findById(id);
        if (optionalReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "review with id: " + id + " does not exist");
        }
        return optionalReview.get();
    }
}
