package cn.jiangsijia.myblog.controller.admin;

import cn.jiangsijia.myblog.po.Type;
import cn.jiangsijia.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 蒋思佳
 * @date 2020/6/15 0015.
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String type(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){

        Page<Type> listType = typeService.listType(pageable);
        model.addAttribute("page",listType);
        return "admin/types";
    }

    @GetMapping("/deleteType")
    public String deleteType(Long id){
        typeService.deleteType(id);
        return "admin/types";
    }

}
