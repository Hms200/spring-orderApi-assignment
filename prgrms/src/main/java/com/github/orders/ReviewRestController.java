package com.github.orders;

import static com.github.utils.ApiUtils.error;
import static com.github.utils.ApiUtils.success;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.github.orders.OrderService;
import com.github.orders.Review;
import com.github.products.ProductService;
import com.github.security.JwtAuthentication;
import com.github.utils.ApiUtils.ApiResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {

    @Autowired
    OrderService orderService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    HttpSession session;
    @Autowired
    ProductService productService;

    @PostMapping("{id}/review")
    public ApiResult<?> review(@PathVariable Long id,
                               @RequestBody Review review,
                               @AuthenticationPrincipal JwtAuthentication authentication) throws IllegalArgumentException , IllegalStateException {
        System.out.println("=========  start ================");
        long userId = authentication.id;
        if(review.getContent().equals(null) || review.getContent().equals("")) {
            throw new HttpMessageNotReadableException("empty content");
        }

        Optional<Order> orderOptional = Optional.ofNullable(orderService.getOrderById(id));
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            System.out.println("-------------------get order----" + order.getState() + " , " + order.getReviewSeq().get());
            if (order.getState().equals("COMPLETED") && order.getReviewSeq().get() == 0) {
                System.out.println("-----------------------write new review");
                Review newReview = new Review.Builder()
                        .userSeq(userId)
                        .productSeq(order.getProductId())
                        .content(review.getContent())
                        .createAt(LocalDateTime.now())
                        .build();
                newReview = reviewService.insertReview(newReview);
                orderService.updateReviewSeq(order.getSeq(), newReview.getSeq());
                productService.updateProductReviewCount(order.getProductId());
                System.out.println("------------------updated reviewCount-------------------");
                return success(newReview);
            } else if (!order.getState().equals("COMPLETED")) {
                System.out.println("--------------state is not complete-----------------");
                throw new IllegalStateException("state is not complete");
            }
            else if (order.getReviewSeq().get() != 0) {
                System.out.println("-----------------------already exist-----------------");
                throw new HttpMessageNotReadableException("already exist");
            }
        } else {
            System.out.println("-------------------illegalstateexception---------------");
            throw new IllegalStateException("the order state is not COMPLETED");
        }
        return null;
    }

}
