package cn.tedu.sp4.service;
import cn.tedu.entity.Item;
import cn.tedu.entity.Order;
import cn.tedu.entity.User;
import cn.tedu.service.OrderService;
import cn.tedu.sp4.feign.ItemClient;
import cn.tedu.sp4.feign.UserClient;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemClient itemClient;
    @Autowired
    private UserClient userClient;

    @Override
    public Order getOrder(String orderId) {
        log.info("获取订单，orderId="+orderId);

        // 远程调用商品，获取商品列表
        JsonResult<List<Item>> items = itemClient.getItems(orderId);
        // 远程调用用户，获取用户数据
        JsonResult<User> user = userClient.getUser(8);//真实项目中应该获取已登录用户的id

        Order order = new Order();
        order.setId(orderId);
        order.setItems(items.getData());
        order.setUser(user.getData());
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("添加订单："+order);

        // 远程调用商品，减少库存
        itemClient.decreaseNumber(order.getItems());
        // 远程调用用户，增加积分
        userClient.addScore(order.getUser().getId(), 1000);
    }
}