package cn.foobar.forum.controller;

import cn.foobar.forum.controller.request.PostParams;
import cn.foobar.forum.controller.request.Result;
import cn.foobar.forum.entity.Post;
import cn.foobar.forum.entity.Topic;
import cn.foobar.forum.entity.User;
import cn.foobar.forum.service.PostService;
import cn.foobar.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author PostController
 * @Date 2019/4/6 14:48
 * @Version 1.0.0
 * @Description
 **/
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    // 用户发表帖子
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Result addPost(HttpServletRequest request, @RequestBody PostParams postParams) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.builder().status("200").message("请先登录!").build();
        }

        // TODO 参数校验

        Long topicId = postParams.getTopicId();
        Topic topic = topicService.getTopicByTopicId(topicId);
        if (topic == null) {
            return Result.builder().status("200").message("主题不存在!").build();
        }

        Post post = postParams.getPost();
        post.setPostBy(user);
        post.setPostTopic(topic);

        postService.savePost(post);

        // 处理用户敏感信息
        post.getPostTopic().getTopicBy().setUserPass(null);
        // 处理贴主敏感信息
        post.getPostBy().setUserPass(null); // TODO
        return Result.builder().status("200").message("帖子创建成功!").entity(post).build();
    }

    // 根据 topic 获取 所有帖子
    @GetMapping("/{topicId}")
    public Result findPostsByTopicId(@PathVariable Long topicId) {

        // 根据 topicId 获取 Topic
        Topic topic = topicService.getTopicByTopicId(topicId);
        if (topic == null) {
            return Result.builder().status("200").message("主题不存在!").build();
        }

        List<Post> posts = postService.getPostsByPostTopic(topic);
        posts.forEach(post -> {
            post.getPostBy().setUserPass(null);
            post.getPostTopic().getTopicBy().setUserPass(null);
        });

        return Result.builder().status("200").message("成功获取 "+ topic.getTopicSubject() +" 主题下所有帖子!").entity(posts).build();
    }

    // 用户删除帖子
}
