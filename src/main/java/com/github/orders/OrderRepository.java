package com.github.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(long id);

    List<Order> findAll(long offset, int size);

    int acceptOrder(long id);

    int rejectOrder(long id, String msg);

    int shippingOrder(long id);

    int completeOrer(long id);

    void updateReviewSeq(long id, long reviewSeq);

}
