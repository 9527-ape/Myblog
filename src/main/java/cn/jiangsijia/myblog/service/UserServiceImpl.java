package cn.jiangsijia.myblog.service;

import cn.jiangsijia.myblog.dao.UserRepository;
import cn.jiangsijia.myblog.po.User;
import cn.jiangsijia.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 蒋思佳
 * @date 2020/6/13 0013.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
