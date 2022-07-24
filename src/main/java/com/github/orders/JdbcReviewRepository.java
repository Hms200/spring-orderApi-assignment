package com.github.orders;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import static com.github.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcReviewRepository implements ReviewRepository{

    private final JdbcTemplate jdbcTemplate;

    private final HttpSession session;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate, HttpSession session) {
        this.jdbcTemplate = jdbcTemplate;
        this.session = session;
    }

    @Override
    public Optional<Review> findById(long id) {
        List<Review> review = jdbcTemplate.query(
                "SELECT * FROM reviews WHERE seq = ?", mapper, id);
        return Optional.ofNullable(review.isEmpty() ? null : review.get(0));
    }



    @Override
    public Optional<Review> findByUserId(long userId) {
        List<Review> reviews = jdbcTemplate.query(
                "SELECT * FROM reviews WHERE user_seq = ?", mapper, userId);
        return Optional.ofNullable(reviews.isEmpty() ? null : reviews.get(0));
    }

    @Override
    public long insertReview(Review review) {
        String query = "insert into reviews (user_seq, product_seq, content) values (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"seq"});
            preparedStatement.setLong(1, (long) review.getUserSeq());
            preparedStatement.setLong(2, review.getProductSeq());
            preparedStatement.setString(3, review.getContent());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review.Builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productSeq(rs.getLong("product_seq"))
                    .content(rs.getString("content"))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();

}
