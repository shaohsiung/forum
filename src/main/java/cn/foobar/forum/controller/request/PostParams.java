package cn.foobar.forum.controller.request;

import cn.foobar.forum.entity.Post;
import lombok.Getter;

/**
 * @Author PostParams
 * @Date 2019/4/6 16:11
 * @Version 1.0.0
 * @Description
 **/
@Getter
public class PostParams {
    private Post post;
    private Long topicId;
}
