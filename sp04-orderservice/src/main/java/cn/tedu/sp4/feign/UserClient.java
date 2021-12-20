package cn.tedu.sp4.feign;


import cn.tedu.entity.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
* userClient.getUser(8);
*       调用后台模块服务器： http://USER-SERVICE的服务器地址
*       调用指定路径:http://USER-SERVICE的服务器地址/{userId}
*       向路径提交参数：
*
*
* */
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    @PostMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable Integer userId,
                           @RequestParam("score") Integer score);
}
