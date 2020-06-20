package cn.jiangsijia.myblog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 蒋思佳
 * @date 2020/6/13 0013.
 */
@Entity(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    //不能为空
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
