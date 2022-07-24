package com.github.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static com.github.utils.DateTimeUtils.dateTimeOf;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> findById(long id) {
        List<Order> orders = jdbcTemplate.query("SELECT * FROM orders WHERE seq = ?", mapper, id);
        return Optional.ofNullable(orders.isEmpty() ? null : orders.get(0));
    }

    @Override
    public List<Order> findAll(long offset, int size) {
        return jdbcTemplate.query(
                "SELECT * FROM orders ORDER BY seq DESC LIMIT ? OFFSET ?", mapper, size, offset);
    }

    @Override
    public int acceptOrder(long id) {
        return jdbcTemplate.update("UPDATE orders SET state = 'ACCEPTED' WHERE seq = ? ", id);
    }

    @Override
    public int rejectOrder(long id, String msg) {
        return jdbcTemplate.update("UPDATE orders SET state = 'REJECTED', rejected_at = ?, reject_msg = ? WHERE seq = ?", LocalDateTime.now(), msg, id);
    }

    @Override
    public int shippingOrder(long id) {
        return jdbcTemplate.update("UPDATE orders SET state = 'SHIPPING' where seq = ?", id);
    }

    @Override
    public int completeOrer(long id) {
        return jdbcTemplate.update("UPDATE orders SET state = 'COMPLETED', completed_at = ? WHERE seq = ?", LocalDateTime.now(), id);
    }




    @Override
    public void updateReviewSeq(long id, long reviewSeq) {
        jdbcTemplate.update("UPDATE orders SET review_seq = ? WHERE seq = ?", reviewSeq, id);
    }




    static RowMapper<Order> mapper = (rs, rowNum) ->
            new Order.Builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .productId(rs.getLong("product_seq"))
                    .reviewSeq(rs.getLong("review_seq"))
                    .state(rs.getString("state"))
                    .requestMessage(rs.getString("request_msg"))
                    .rejectMessage(rs.getString("reject_msg"))
                    .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
                    .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
                    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();

}
