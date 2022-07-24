package com.github.orders;

import java.util.List;
import java.util.Optional;

import com.github.orders.Order;
import com.github.orders.OrderRepository;
import com.github.configures.web.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ReviewRepository reviewRepository;

    public OrderService(OrderRepository orderRepository, ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Order getOrderById(long id) {
        Order order = orderRepository.findById(id).get();
        if (order.getReviewSeq().get() != 0 ) {
            Review review = reviewRepository.findById(order.getReviewSeq().get()).get();
            order.setReview(review);
        }
        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrder(Pageable pageable) {
        long offset = pageable.getOffset();
        int size = pageable.getSize();
        List<Order> orders = orderRepository.findAll(offset, size);
        orders.forEach(order -> {
            if (order.getReviewSeq().get() != 0){
                Optional<Review> reviewOptional = reviewRepository.findById(order.getReviewSeq().get());
                order.setReview(reviewOptional.get());
            }
        });
        return orders;
    }

    @Transactional
    public boolean acceptOrder(long id) {
        Order order = orderRepository.findById(id).get();
        if (!order.getState().equals("REQUESTED")){
            return false;
        }
        int result = orderRepository.acceptOrder(id);
        return result == 1 ? true : false;
    }

    @Transactional
    public boolean rejectOrder(long id, String msg) {
        Order order = orderRepository.findById(id).get();
        if (!order.getState().equals("REQUESTED")) {
            return false;
        }
        int result = orderRepository.rejectOrder(id, msg);
        System.out.println("--------------  result " + result + " --------------");
        return result == 1 ? true : false;
    }

    @Transactional
    public boolean shippingOrder(long id) {
        Order order = orderRepository.findById(id).get();
        if (!order.getState().equals("ACCEPTED")) {
            return false;
        }
        int result = orderRepository.shippingOrder(id);
        return result == 1 ? true : false;
    }

    @Transactional
    public boolean completeOrder(long id) {
        Order order = orderRepository.findById(id).get();
        if (!order.getState().equals("SHIPPING")) {
            return false;
        }
        int result = orderRepository.completeOrer(id);
        return result == 1 ? true : false;
    }

    @Transactional
    public void updateReviewSeq (long id, long reviewSeq) {
        orderRepository.updateReviewSeq(id, reviewSeq);
    }


}
