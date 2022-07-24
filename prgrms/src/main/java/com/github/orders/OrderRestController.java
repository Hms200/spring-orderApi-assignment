package com.github.orders;

import java.util.HashMap;
import java.util.List;

import com.github.configures.web.Pageable;
import com.github.security.JwtAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.utils.ApiUtils.error;
import static com.github.utils.ApiUtils.success;
import com.github.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    @Autowired
    OrderService orderService;

    @GetMapping
    public ApiResult<List<Order>> findAll(Pageable pageable) {
        System.out.println("======= find All ============");
        List<Order> orderList = orderService.getAllOrder(pageable);
        return success(orderList);
    }

    @GetMapping("{id}")
    public ApiResult<Order> findById (@PathVariable long id) {
        System.out.println("========= finde one order id = " + id + " =============");
        Order order = orderService.getOrderById(id);
        return success(order);
    }

    @PatchMapping("{id}/accept")
    public ApiResult<Boolean> accept(@PathVariable long id) {
        return success(orderService.acceptOrder(id));
    }

    @PatchMapping("{id}/reject")
    public ApiResult<Boolean> reject(@PathVariable long id, @RequestBody HashMap<String, String> msg) {
        System.out.println(msg.toString());
        if (!msg.containsKey("message")) {
            System.out.println("message is valid");
            return success(false);
        }
        return success(orderService.rejectOrder(id, msg.get("message")));
    }

    @PatchMapping("{id}/shipping")
    public ApiResult<Boolean> shipping(@PathVariable long id) {
        return success(orderService.shippingOrder(id));
    }

    @PatchMapping("{id}/complete")
    public ApiResult<Boolean> complete(@PathVariable long id) {
        return success(orderService.completeOrder(id));
    }

}
