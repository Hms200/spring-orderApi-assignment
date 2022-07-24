package com.github.orders;

import java.time.LocalDateTime;
import static org.springframework.beans.BeanUtils.copyProperties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderDto {

    private Long seq;

    private Long userSeq;

    private Long productId;

    private Long reviewSeq;

    private String state;

    private String requestMesseage;

    private String rejectMesseage;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createAt;

    public OrderDto(Order source) {
        copyProperties(source, this);

        this.reviewSeq = source.getReviewSeq().orElse(null);
        this.requestMesseage = source.getRequestMessage().orElse(null);
        this.rejectMesseage = source.getRejectMessage().orElse(null);
        this.completedAt = source.getCompletedAt().orElse(null);
        this.rejectedAt = source.getRejectedAt().orElse(null);
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public Long getproductId() {
        return productId;
    }

    public void setproductId(Long productId) {
        this.productId = productId;
    }

    public Long getReviewSeq() {
        return reviewSeq;
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

    public String getRequestMesseage() {
        return requestMesseage;
    }

    public void setRequestMesseage(String requestMesseage) {
        this.requestMesseage = requestMesseage;
    }

    public String getRejectMesseage() {
        return rejectMesseage;
    }

    public void setRejectMesseage(String rejectMesseage) {
        this.rejectMesseage = rejectMesseage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userSeq", userSeq)
                .append("productId", productId)
                .append("reviewSeq", reviewSeq)
                .append("state", state)
                .append("requestMesseage", requestMesseage)
                .append("rejectMesseage", rejectMesseage)
                .append("completedAt", completedAt)
                .append("rejectedAt", rejectedAt)
                .append("createAt", createAt)
                .toString();
    }

}
