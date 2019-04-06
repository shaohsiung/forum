package cn.foobar.forum.service;

import cn.foobar.forum.entity.Post;

import java.util.List;

public interface PostService {
    void createPost(Post post);
    void deletePost(Long postId, Long userId);
    void editPost(Post post);

    /**
     * 根据 TopicId 获取所有帖子
     * @param TopicId
     * @return
     */
    List<Post> getPostsByTopicId(Long TopicId);
}
