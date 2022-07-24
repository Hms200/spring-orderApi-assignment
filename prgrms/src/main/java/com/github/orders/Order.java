package com.github.orders;

import java.time.LocalDateTime;
import java.util.Optional;
import static java.util.Optional.ofNullable;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Order {

    private final Long seq;

    private Long userSeq;

    private Long productId;

    private Long reviewSeq;

    private String state;

    private String requestMessage;

    private String rejectMessage;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private final LocalDateTime createAt;

    private Review review;

    public Order(Long seq, Long userSeq, Long productId, Long reviewSeq, String state, String requestMessage,
                 String reject_msg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt, Review review) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productId = productId;
        this.reviewSeq = reviewSeq;
        this.state = defaultIfNull(state, "REQUESTED");
        this.requestMessage = requestMessage;
        this.rejectMessage = reject_msg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = defaultIfNull(createAt, LocalDateTime.now());
        this.review = defaultIfNull(review, null);
    }

    public Order(Long seq, Long userSeq, Long productId, Long reviewSeq, String state, String requestMessage,
                 String reject_msg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productId = productId;
        this.reviewSeq = reviewSeq;
        this.state = defaultIfNull(state, "REQUESTED");
        this.requestMessage = requestMessage;
        this.rejectMessage = reject_msg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = defaultIfNull(createAt, LocalDateTime.now());
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Optional<Long> getReviewSeq() {
        return ofNullable(reviewSeq);
    }

    public void setReviewSeq(Long reviewSeq) {
        this.reviewSeq = reviewSeq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Optional<String> getRequestMessage() {
        return ofNullable(requestMessage);
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public Optional<String> getRejectMessage() {
        return ofNullable(rejectMessage);
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public Optional<LocalDateTime> getCompletedAt() {
        return ofNullable(completedAt);
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Optional<LocalDateTime> getRejectedAt() {
        return ofNullable(rejectedAt);
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setReview (Review review) {
        this.review = review;
    }

    public Review getReview () {
        return review;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userSeq", userSeq)
                .append("productId", productId)
                .append("reviewSeq", reviewSeq)
                .append("state", state)
                .append("requestMessage", requestMessage)
                .append("rejectMessage", rejectMessage)
                .append("completedAt", completedAt)
                .append("rejectedAt", rejectedAt)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productId;
        private Long reviewSeq;
        private String state;
        private String requestMessage;
        private String rejectMessage;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;
        private Review review;

        public Builder(){}

        public Builder(Order order) {
            this.seq = order.seq;
            this.userSeq = order.userSeq;
            this.productId = order.productId;
            this.reviewSeq = order.reviewSeq;
            this.state = order.state;
            this.requestMessage = order.requestMessage;
            this.rejectMessage = order.rejectMessage;
            this.completedAt = order.completedAt;
            this.rejectedAt = order.rejectedAt;
            this.createAt = order.createAt;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder reviewSeq(Long reviewSeq) {
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder requestMessage(String requestMessage) {
            this.requestMessage = requestMessage;
            return this;
        }

        public Builder rejectMessage(String rejectMessage) {
            this.rejectMessage = rejectMessage;
            return this;
        }

        public Builder completedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public Builder rejectedAt(LocalDateTime rejectedAt) {
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Builder review(Review review) {
            this.review = review;
            return this;
        }

        public Order build(){
            return new Order (
                    seq,
                    userSeq,
                    productId,
                    reviewSeq,
                    state,
                    requestMessage,
                    rejectMessage,
                    completedAt,
                    rejectedAt,
                    createAt,
                    review
            );
        }

    }








}
