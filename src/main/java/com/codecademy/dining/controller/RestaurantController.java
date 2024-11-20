package com.codecademy.dining.controller;

import com.codecademy.dining.model.Restaurant;
import com.codecademy.dining.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dining/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public Iterable<Restaurant> findAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @RequestMapping(path="/", method=RequestMethod.POST)
    public Restaurant createNewRestaurant(@RequestBody Restaurant newRestaurant) {
        return this.restaurantRepository.save(newRestaurant);
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Restaurant findRestaurantById(@PathVariable("id") long id) {
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(id);
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "restaurant with id: " + id + " does not exist");
        }
        return optionalRestaurant.get();
    }
}
