package cn.foobar.forum.repository;

import cn.foobar.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author UserRepository
 * @Date 2019/4/6 10:08
 * @Version 1.0.0
 * @Description
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserNameAndUserPass(String userName, String userPass);
}
