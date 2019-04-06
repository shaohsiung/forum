package cn.foobar.forum;

import cn.foobar.forum.entity.User;
import cn.foobar.forum.entity.UserState;
import cn.foobar.forum.repository.UserRepository;
import cn.foobar.forum.util.SHA1Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForumApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {

	}

	@Test
	public void initAdmin() {
		User user = new User();
		user.setUserName("admin");
		user.setUserPass(SHA1Utils.encryptThisString("admin"));
		user.setUserEmail("admin@gmail.com");
		user.setUserLevel(UserState.ADMIN);
		userRepository.save(user);
	}

}
