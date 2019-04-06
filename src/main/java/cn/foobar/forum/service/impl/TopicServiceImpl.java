package cn.foobar.forum.service.impl;

import cn.foobar.forum.entity.Category;
import cn.foobar.forum.entity.Topic;
import cn.foobar.forum.repository.TopicRepository;
import cn.foobar.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author TopicServiceImpl
 * @Date 2019/4/6 12:50
 * @Version 1.0.0
 * @Description
 **/
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Topic topic) {
        topicRepository.delete(topic);
    }

    @Override
    public List<Topic> getTopicsByCategory(Category category) {
        return topicRepository.getTopicsByTopicCat(category);
    }

    @Override
    public Topic getTopicByTopicId(Long topicId) {
        return topicRepository.findById(topicId).get();
    }
}
