package cn.jiangsijia.myblog.service;

import cn.jiangsijia.myblog.NotFoundException;
import cn.jiangsijia.myblog.dao.TypeRepositoty;
import cn.jiangsijia.myblog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author 蒋思佳
 * @date 2020/6/15 0015.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepositoty typeRepositoty;

    //添加
    @Transactional//事务
    @Override
    public Type saveType(Type type) {
        return typeRepositoty.save(type);
    }

    @Transactional//事务
    @Override
    public void deleteType(Long id) {
       typeRepositoty.deleteById(id);
    }

    @Transactional//事务
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepositoty.getOne(id);
        if(t == null){
            throw  new NotFoundException("查询不存在该类型！");
        }else{
            BeanUtils.copyProperties(type,t);
            return typeRepositoty.save(t);
        }
    }

    @Transactional//事务
    @Override
    public Type getType(Long id) {
        Type type = typeRepositoty.getOne(id);
        return type;
    }

    @Transactional//事务
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepositoty.findAll(pageable);
    }

    @Transactional//事务
    @Override
    public Type getTypeByName(String name) {
        Type type = typeRepositoty.findByName(name);
        return type;
    }
}
