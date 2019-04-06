package cn.foobar.forum.controller;

import cn.foobar.forum.controller.request.Result;
import cn.foobar.forum.entity.Category;
import cn.foobar.forum.entity.User;
import cn.foobar.forum.entity.UserState;
import cn.foobar.forum.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author CategoryController
 * @Date 2019/4/6 11:51
 * @Version 1.0.0
 * @Description
 **/
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Result createCategory(HttpServletRequest request, @RequestBody Category category) {

        // 用户登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.builder().status("200").message("请先登录!").build();
        }

        // 用户为管理员
        if (!user.getUserLevel().equals(UserState.ADMIN)) {
            return Result.builder().status("200").message("您不是管理员, 无权操作!").build();
        }

        // TODO 数据校验
        categoryService.createCategory(category);
        // 查看category
        log.info("Category: {}", category);
        return Result.builder().status("201").message("分类创建成功!").entity(category).build();
    }
}
