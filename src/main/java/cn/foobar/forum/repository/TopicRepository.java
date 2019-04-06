package cn.foobar.forum.repository;

import cn.foobar.forum.entity.Category;
import cn.foobar.forum.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    /**
     * 获取分类下所有主题
     * @param category
     * @return
     */
    List<Topic> getTopicsByTopicCat(Category category);
}
