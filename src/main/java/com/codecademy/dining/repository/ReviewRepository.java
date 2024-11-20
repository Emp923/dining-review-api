package com.codecademy.dining.repository;

import com.codecademy.dining.model.Review;
import com.codecademy.dining.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByStatus(ReviewStatus status);
    List<Review> findByRestaurantId(long restaurantId);
    List<Review> findByStatusAndRestaurantId(ReviewStatus status, long restaurantId);
}
