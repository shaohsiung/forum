package cn.foobar.forum.service;

import cn.foobar.forum.entity.Post;
import cn.foobar.forum.entity.Topic;

import java.util.List;

public interface PostService {
    /**
     * 用户创建 或 更新 帖子
     * @param post
     */
    void savePost(Post post);

    /**
     * 用户删除帖子
     * @param post
     */
    void deletePost(Post post);

    /**
     * 根据 topicId 获取所有帖子
     * @param topic
     * @return
     */
    List<Post> getPostsByPostTopic(Topic topic);
}
