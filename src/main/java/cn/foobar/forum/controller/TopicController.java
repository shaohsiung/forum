package cn.foobar.forum.controller;

import cn.foobar.forum.controller.request.Result;
import cn.foobar.forum.controller.request.TopicParams;
import cn.foobar.forum.entity.Category;
import cn.foobar.forum.entity.Topic;
import cn.foobar.forum.entity.User;
import cn.foobar.forum.repository.TopicRepository;
import cn.foobar.forum.service.CategoryService;
import cn.foobar.forum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author TopicController
 * @Date 2019/4/6 12:52
 * @Version 1.0.0
 * @Description
 **/
@RestController
@RequestMapping("topic")
@Slf4j
public class TopicController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TopicService topicService;

    /**
     * 用户发表主题
     * @param request
     * @param topicParams
     * @return
     */
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Result createTopic(HttpServletRequest request, @RequestBody TopicParams topicParams) {

        // 判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.builder().status("200").message("请先登录!").build();
        }

        // TODO 参数校验

        // 根据catId获取分类
        Category category = categoryService.findCategoryById(topicParams.getCatId());
        if (category == null) {
            return Result.builder().status("200").message("请指定主题的分类!").build();
        }

        Topic topic = topicParams.getTopic();
        topic.setTopicBy(user);
        topic.setTopicCat(category);
        topicService.saveTopic(topic);

        // 处理用户敏感信息
        topic.getTopicBy().setUserPass(null);
        return Result.builder().status("200").message("主题发表成功!").entity(topic).build();
    }

    /**
     * 获取分类下所有主题
     * @param catId
     * @return
     */
    @GetMapping("/{catId}")
    public Result getTopicsByCatId(@PathVariable Long catId) {

        // 根据 catId 获取分类
        Category category = categoryService.findCategoryById(catId);
        if (category == null) {
            return Result.builder().status("200").message("不存在的分类!").build();
        }

        log.info("category {}", category);

        List<Topic> topics = topicService.getTopicsByCategory(category);

        // 清空用户敏感信息
        topics.forEach(topic -> {
            topic.getTopicBy().setUserPass(null);
        });
        return Result.builder().status("200").message("获取 "+ category.getCatName() +" 下所有主题.").entity(topics).build();
    }

    /**
     * 根据 topicId 删除 topic
     * @param request
     * @param topicId
     * @return
     */
    @DeleteMapping("/{topicId}")
    public Result deleteTopic(HttpServletRequest request, @PathVariable Long topicId) {

        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.builder().status("200").message("清先登录!").build();
        }

        // 获取要删除的主题
        Topic topic = topicService.getTopicByTopicId(topicId);
        if (! (topic != null && topic.getTopicBy().getUserId().equals(user.getUserId())) ) {
            return Result.builder().status("200").message("你无权删除该主题!").build();
        }

        String topicSubject = topic.getTopicSubject();

        topicService.deleteTopic(topic);

        return Result.builder().status("200").message("主题 "+ topicSubject +" 删除成功!").build();
    }

    @PostMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result editTopic(HttpServletRequest request, @RequestBody Topic topic) {

        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.builder().status("200").message("清先登录!").build();
        }

        // 根据 topicId 获取旧topic
        Topic oldTopic = topicService.getTopicByTopicId(topic.getTopicId());

        // 判断文章是否属于当前用户
        if (!( oldTopic != null && oldTopic.getTopicBy().getUserId().equals(user.getUserId()) )) {
            return Result.builder().status("200").message("你无权编辑该主题!").build();
        }

        // 编辑主题
        oldTopic.setTopicSubject(topic.getTopicSubject());
        topicService.saveTopic(oldTopic); // TODO 修改 方法名称 -> 为什么是save 而不是 create

        log.info("oldTopic {}", oldTopic);
        oldTopic.getTopicBy().setUserPass(null);

        return Result.builder().status("200").message("主题编辑成功!").entity(oldTopic).build();
    }
}
