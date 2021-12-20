package cn.tedu.sp2.controller;

import cn.tedu.entity.Item;
import cn.tedu.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    // 获取订单的商品列表
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(
            @PathVariable String orderId) throws InterruptedException {
        List<Item> items = itemService.getItems(orderId);
        if(Math.random()<0.9){
            int t = new Random().nextInt(5000);
            log.info("暂停"+t);
            Thread.sleep(t);
        }
        return JsonResult.build().code(200).data(items);
    }

    // 减少商品库存
    // @RequestBody 接收 post 请求的协议体数据
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(
            @RequestBody List<Item> items) {
        itemService.decreaseNumber(items);
        return JsonResult.build().code(200).msg("减少库存成功");
    }



    @GetMapping("/favicon.ico")
    public void ico() {
    }
}
