package cn.jiangsijia.myblog.dao;

import cn.jiangsijia.myblog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 蒋思佳
 * @date 2020/6/15 0015.
 */
public interface TypeRepositoty extends JpaRepository<Type, Long>{
    Type findByName(String name);
}
