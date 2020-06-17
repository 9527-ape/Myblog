package cn.jiangsijia.myblog.service;

import cn.jiangsijia.myblog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 蒋思佳
 * @date 2020/6/15 0015.
 */
public interface TypeService {

    //添加
    Type saveType(Type type);

    void deleteType(Long id);

    Type updateType(Long id ,Type type);

    //查询
    Type getType(Long id);

    //分页
    Page<Type> listType(Pageable pageable);

    Type getTypeByName(String name);
}
