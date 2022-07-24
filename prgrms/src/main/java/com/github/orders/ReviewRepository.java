package com.github.orders;

import com.github.orders.Review;

import java.util.Optional;

public interface ReviewRepository {

    Optional<Review> findById(long id);

    long insertReview(Review review);

    Optional<Review> findByUserId(long userId);

}
