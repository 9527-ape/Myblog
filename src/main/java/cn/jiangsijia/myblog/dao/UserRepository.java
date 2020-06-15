package cn.jiangsijia.myblog.dao;

import cn.jiangsijia.myblog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 蒋思佳
 * @date 2020/6/13 0013.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
