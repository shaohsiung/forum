package cn.foobar.forum.service.impl;

import cn.foobar.forum.entity.Post;
import cn.foobar.forum.entity.Topic;
import cn.foobar.forum.repository.PostRepository;
import cn.foobar.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author PostServiceImpl
 * @Date 2019/4/6 14:42
 * @Version 1.0.0
 * @Description
 **/
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> getPostsByPostTopic(Topic topic) {
        return postRepository.getPostsByPostTopic(topic);
    }
}
