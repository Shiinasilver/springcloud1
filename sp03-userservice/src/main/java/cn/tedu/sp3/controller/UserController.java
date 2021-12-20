package cn.tedu.sp3.controller;
import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    // 获取用户
    @GetMapping("/{userId}")
    public JsonResult<User> getUser(
            @PathVariable Integer userId) {
        User user = userService.getUser(userId);
        return JsonResult.build().code(200).data(user);
    }
    // 增加积分
    // 访问路径： http://localhost:8101/8/score?score=1000
    @GetMapping("/{userId}/score")
    public JsonResult<?> addScore(@PathVariable Integer userId,
                                  Integer score) {
        userService.addScore(userId, score);
        return JsonResult.build().code(200).msg("增加积分成功");
    }

    @GetMapping("/favicon.ico")
    public void ico() {
    }
}