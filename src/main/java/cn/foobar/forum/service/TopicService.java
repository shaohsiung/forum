package cn.foobar.forum.service;

import cn.foobar.forum.entity.Category;
import cn.foobar.forum.entity.Topic;

import java.util.List;

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
    void saveTopic(Topic topic);

    /**
     * 用户删除主题
     * @param topic
     */
    void deleteTopic(Topic topic);

    /**
     * 根据 catId 获取所有 Topic
     * @param category
     * @return
     */
    List<Topic> getTopicsByCategory(Category category);

    /**
     * 根据 topicId 获取 topic
     * @param topicId
     * @return
     */
    Topic getTopicByTopicId(Long topicId);
}
