package cn.tedu.sp4.controller;
import cn.tedu.entity.Item;
import cn.tedu.entity.Order;
import cn.tedu.entity.User;
import cn.tedu.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    //获取订单
    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(
            @PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        return JsonResult.build().code(200).data(order);
    }
    @GetMapping("/add")
    public JsonResult<?> addOrder() {
        Order order = new Order();
        order.setId("o87i6uj5y4tr3l87k6ju5y4t3");
        order.setItems(Arrays.asList(new Item[]{
                new Item(1,"商品1",1),new Item(2,"商品2",3),
                new Item(3,"商品3",1),new Item(4,"商品4",5),
                new Item(5,"商品5",7)
        }));
        order.setUser(new User(8, null, null));
        orderService.addOrder(order);
        return JsonResult.build().code(200).msg("添加订单成功");
    }

    @GetMapping("/favicon.ico")
    public void ico() {
    }
}
