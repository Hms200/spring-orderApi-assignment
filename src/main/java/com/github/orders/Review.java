package com.github.orders;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;

public class Review {

    private Long seq;

    private Long userSeq;

    private Long productId;

    private String content;

    private LocalDateTime createAt;

    public Review(){}

    public Review(String content){
        this(null, null, null, content, null);
    }

    public Review(Long seq, Long userSeq, Long productSeq, String content, LocalDateTime createAt) {
        checkArgument(isNotEmpty(content), "content must be provided");

        this.seq = seq;
        this.userSeq = userSeq;
        this.productId = productSeq;
        this.content = content;
        this.createAt = defaultIfNull(createAt, LocalDateTime.now());
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

    public Long getProductSeq() {
        return productId;
    }

    public void setProductSeq(Long productSeq) {
        this.productId = productSeq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        checkArgument(isNotEmpty(content), "content must be provided");
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userSeq", userSeq)
                .append("productSeq", productId)
                .append("content", content)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private String content;
        private LocalDateTime createAt;

        public Builder() {}

        public Builder(Review review) {
            this.seq = review.seq;
            this.userSeq = review.userSeq;
            this.productSeq = review.productId;
            this.content = review.content;
            this.createAt = review.createAt;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Review build(){
            return new Review(
                    seq,
                    userSeq,
                    productSeq,
                    content,
                    createAt
            );
        }

    }







}
