package cn.jiangsijia.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 蒋思佳
 * @date 2020/5/16 0016.
 */

//页面转跳
@Controller
public class JumpController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/types")
    public String types(){
        return "types";
    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

}
