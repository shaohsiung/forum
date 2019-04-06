package cn.foobar.forum.controller;

import cn.foobar.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private PostRepository postRepository;

    // 用户发表帖子
    // 用户删除帖子
    // 根据 topic 获取 所有帖子
}
