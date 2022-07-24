package com.github.orders;

import java.util.Optional;

import com.github.orders.Review;
import com.github.orders.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review insertReview(Review review) {
        long reviewSeq = reviewRepository.insertReview(review);
        Optional<Review> newReview = reviewRepository.findById(reviewSeq);
        return newReview.get();
    }

    @Transactional(readOnly = true)
    public Optional<Review> findByUserId(long userId) {
        return reviewRepository.findByUserId(userId);
    }

}
