package cn.foobar.forum.controller.request;

import cn.foobar.forum.entity.Topic;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author TopicParams
 * @Date 2019/4/6 13:32
 * @Version 1.0.0
 * @Description
 **/
@Getter
public class TopicParams {
    private Topic topic;
    private Long catId;
}
