package cn.foobar.forum.repository;

import cn.foobar.forum.entity.Post;
import cn.foobar.forum.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author PostRepository
 * @Date 2019/4/6 10:24
 * @Version 1.0.0
 * @Description
 **/
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> getPostsByPostTopic(Topic topic);
}
