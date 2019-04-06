package cn.foobar.forum.service;

import cn.foobar.forum.entity.Topic;

/**
 * @Author TopicService
 * @Date 2019/4/6 10:18
 * @Version 1.0.0
 * @Description
 **/
public interface TopicService {

    /**
     * 用户创建主题
     * @param topic
     */
    void createTopic(Topic topic);

    /**
     * 用户编辑主题
     * @param topic
     */
    void editTopic(Topic topic);

    /**
     * 用户删除主题
     * @param topicId
     */
    void deleteTopic(Long userId, Long topicId);
}
