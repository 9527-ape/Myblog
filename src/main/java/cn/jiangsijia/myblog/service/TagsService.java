package cn.jiangsijia.myblog.service;

import cn.jiangsijia.myblog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 蒋思佳
 * @date 2020/6/20 0020.
 */
public interface TagsService {
    //添加
    Tag saveTag(Tag tag);

    void deleteTag(Long id);

    Tag updateTag(Long id ,Tag tag);

    //查询
    Tag getTag(Long id);

    //分页
    Page<Tag> listTags(Pageable pageable);

    Tag getTagByName(String name);

}
