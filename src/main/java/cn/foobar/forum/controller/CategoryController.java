package cn.foobar.forum.controller;

import cn.foobar.forum.controller.request.Result;
import cn.foobar.forum.entity.Category;
import cn.foobar.forum.entity.User;
import cn.foobar.forum.entity.UserState;
import cn.foobar.forum.repository.CategoryRepository;
import cn.foobar.forum.service.CategoryService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

/**
 * @Author CategoryController
 * @Date 2019/4/6 11:51
 * @Version 1.0.0
 * @Description
 **/
@RestController
@RequestMapping(path = "/category", produces = "application/json")
@Slf4j
@CrossOrigin(origins = "*")  // 允许跨域请求
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 说明：这里注入 categoryRepository 是为了测试 RESTful 支持
     */
    @Autowired
    private CategoryRepository catRepo;

//    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Result createCategory(HttpServletRequest request, @RequestBody Category category) {
//
//        // 用户登录
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
//            return Result.builder().status("200").message("请先登录!").build();
//        }
//
//        // 用户为管理员
//        if (!user.getUserLevel().equals(UserState.ADMIN)) {
//            return Result.builder().status("200").message("您不是管理员, 无权操作!").build();
//        }
//
//        // TODO 数据校验
//        categoryService.createCategory(category);
//        // 查看category
//        log.info("Category: {}", category);
//        return Result.builder().status("201").message("分类创建成功!").entity(category).build();
//    }

//    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public Result getAllCategory() {
//        List<Category> categories = categoryService.getAllCategories();
//        return Result.builder().status("200").message("获取所有分类成功!").entity(categories).build();
//    }

    /**
     * 功能：获取 最近添加的分类（Id降序） 列表（分页）
     *
     * 目的：演示 RESTful GET
     *
     * TODO 优化：
     *          1.将数据存储到 redis 或 Mongo
     *          2.提取分页属性到配置类中
     *
     * @return 分页之后的category对象
     */
    @GetMapping("/recent")
    public Iterable<Category> recentCategories() {
        // 设置分页信息
        PageRequest page = PageRequest.of(0, 4, Sort.by("catId").descending());
        // 调用repo
        return catRepo.findAll(page).getContent();
    }

    /**
     * 功能：添加 category
     *
     * 目的：演示 RESTful POST
     *
     * @param category 添加后的 category
     *
     * @return 添加之后的 category
     */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Category postCategory(@RequestBody Category category) { // RequestBody将JSON序列化为Category对象
        return catRepo.save(category);
    }

    /**
     * 功能：更新 category 全改
     *
     * 目的：演示 RESTful PUT
     *
     * @param category 修改后的 category
     *
     * @return 更新之后的 category
     */
    @PutMapping("/{catId}") // 分类Id
    public Category putCategory(@RequestBody Category category) {
        return catRepo.save(category);
    }

    /**
     * 功能：部分更新 category
     *
     * 目的：演示 RESTful PATCH
     *
     * HINT: 特别注意 PUT 和 PATCH 的区别
     *
     * @param catId 目标分类id
     * @param patch 部分改动过的 category
     *
     * @return 修改之后的category
     */
    @PatchMapping(path = "/{catId}", consumes = "application/json")
    public Category patchCategory(@PathVariable("catId") Long catId, @RequestBody Category patch) {
        // 根据 catId 获取分类对象
        Category category = catRepo.findById(catId).get();
        // 检测是否修改 catName 字段
        if (patch.getCatName() != null) {
            category.setCatName(patch.getCatName());
        }
        // 检测是否修改 catDescription 字段
        if (patch.getCatDescription() != null) {
            category.setCatDescription(patch.getCatDescription());
        }
        // 保存cat对象, 并将其返回
        return catRepo.save(category);
    }

    /**
     * 功能：删除 category
     *
     * 目的：演示 RESTful DELETE
     *
     * @param catId 目标分类id
     *
     */
    @DeleteMapping("/{catId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("catId") Long catId) {
        try {
            catRepo.deleteById(catId);
        } catch (EmptyResultDataAccessException e) {} // catId无相应分类时，会产生异常，这里将其抑制，因为删掉了与没有目标是一样的道理...
    }
}
