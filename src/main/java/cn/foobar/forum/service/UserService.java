package cn.foobar.forum.service;

import cn.foobar.forum.entity.User;

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 用户登录
     * @param userName
     * @param userPass
     * @return
     */
    User login(String userName, String userPass);
}
