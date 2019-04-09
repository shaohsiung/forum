package cn.foobar.forum.controller;

import cn.foobar.forum.controller.request.Result;
import cn.foobar.forum.entity.User;
import cn.foobar.forum.service.UserService;
import cn.foobar.forum.util.SHA1Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author UserController
 * @Date 2019/4/6 10:28
 * @Version 1.0.0
 * @Description
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 处理用户注册请求
     * @param user
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Result register(@Valid @RequestBody User user, BindingResult result) {
        // 参数校验
        if (result.hasErrors()) {
            //log.warn("Binding Errors: {}", result);

            // 将list的数据转移到map中
            Map errMessages = new HashMap();
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                errMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
            });

            // 返回map 保存出错信息
            return Result.builder().status("400").message("注册失败").entity(errMessages).build();
        }


        // 使用SHA1加密用户密码
        String sha1Pass = SHA1Utils.encryptThisString(user.getUserPass());
        user.setUserPass(sha1Pass);

        userService.register(user);
        return Result.builder().status("201").message("用户注册成功!").build();
    }

    /**
     * 处理用户登录请求
     * @param request
     * @param userName
     * @param userPass
     * @return
     */
    @PostMapping(params = {"userName", "userPass"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Result login(HttpServletRequest request, @RequestParam String userName, @RequestParam String userPass) {

        //log.info("userName: {}, userPass: {}", userName, userPass);

        // 判断是否已经登录
        User isLogin = (User) request.getSession().getAttribute("user");
        if (isLogin != null) {
            return Result.builder().status("200").message("请先注销当前账户!").build();
        }

        // 处理用户密码加密
        String sha1Pass = SHA1Utils.encryptThisString(userPass);

        User user = userService.login(userName, sha1Pass);
        if (user == null) {
            return Result.builder().status("200").message("用户名密码错误!").build();
        }

        // 设置用户为登录状态
        request.getSession().setAttribute("user", user);

        user.setUserPass(null);
        return Result.builder().status("200").message("用户登录成功!").entity(user).build();
    }

    /**
     * 处理用户注销请求
     * @param request
     * @return
     */
    @GetMapping(path = "/loginOut")
    @ResponseStatus(HttpStatus.OK)
    public Result loginOut(HttpServletRequest request) {

        // 获取目标用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.builder().status("200").message("您还未登录!").build();
        }

        request.getSession().removeAttribute("user");
        //log.info("Is user login out ? {}", request.getSession().getAttribute("user"));

        return Result.builder().status("200").message("用户 "+ user.getUserName() +" 注销成功!").build();
    }
}
