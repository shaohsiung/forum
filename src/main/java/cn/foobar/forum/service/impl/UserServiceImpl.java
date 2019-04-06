package cn.foobar.forum.service.impl;

import cn.foobar.forum.entity.User;
import cn.foobar.forum.entity.UserState;
import cn.foobar.forum.repository.UserRepository;
import cn.foobar.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author UserServiceImpl
 * @Date 2019/4/6 10:35
 * @Version 1.0.0
 * @Description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(User user) {
        // 设置用户类型为基本用户
        user.setUserLevel(UserState.USER);

        // TODO 使用 异常处理 解决用户名重复问题
        userRepository.save(user);
    }

    @Override
    public User login(String userName, String userPass) {
        return userRepository.findUserByUserNameAndUserPass(userName, userPass);
    }
}
