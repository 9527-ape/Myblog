package cn.jiangsijia.myblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 蒋思佳
 * @date 2020/6/16 0016.
 */
@Controller
@RequestMapping("/admin")
public class TagsController {

    @GetMapping("/tags")
    public String tags(){
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }
}
