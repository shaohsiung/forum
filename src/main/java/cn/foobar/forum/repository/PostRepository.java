package cn.foobar.forum.repository;

import cn.foobar.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author PostRepository
 * @Date 2019/4/6 10:24
 * @Version 1.0.0
 * @Description
 **/
public interface PostRepository extends JpaRepository<Post, Long> {
}
