package cn.jiangsijia.myblog.service;

import cn.jiangsijia.myblog.NotFoundException;
import cn.jiangsijia.myblog.dao.TagsRepository;
import cn.jiangsijia.myblog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
/**
 * @author 蒋思佳
 * @date 2020/6/20 0020.
 */
@Service
public class TagsServiceImpl implements  TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    //添加
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagsRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagsRepository.getOne(id);
        if(t == null){
            throw  new NotFoundException("查询不存在该类型！");
        }else{
            BeanUtils.copyProperties(tag,t);
            return tagsRepository.save(t);
        }
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        Tag tag = tagsRepository.getOne(id);
        return tag;
    }

    @Transactional
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        Page<Tag> listTags = tagsRepository.findAll(pageable);
        return listTags;
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        Tag tag = tagsRepository.findByName(name);
        return tag;
    }
}
