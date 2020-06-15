package cn.jiangsijia.myblog.service;

import cn.jiangsijia.myblog.po.User;

/**
 * @author 蒋思佳
 * @date 2020/6/13 0013.
 */
public interface UserService {

    User checkUser(String username, String password);
}
