package cn.jiangsijia.myblog.dao;

import cn.jiangsijia.myblog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 蒋思佳
 * @date 2020/6/20 0020.
 */
public interface TagsRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
