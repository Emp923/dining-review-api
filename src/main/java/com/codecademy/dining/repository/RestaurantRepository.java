package com.codecademy.dining.repository;

import com.codecademy.dining.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
    List<Restaurant> findByZipCodeAndPeanutScoreLessThanEqualOrderByPeanutScoreDesc(String zipCode, double peanutScore);
}
