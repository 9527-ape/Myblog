package cn.jiangsijia.myblog.controller.admin;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.jiangsijia.myblog.po.Type;
import cn.jiangsijia.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author 蒋思佳
 * @date 2020/6/15 0015.
 */

@Controller
@RequestMapping("/admin")
public class TypesController {

    @Autowired
    private TypeService typeService;

    //分页
    @GetMapping("/types")
    public String types(@PageableDefault(size = 7,sort = {"id"},
                        direction = Sort.Direction.DESC)
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

    @GetMapping("/typesInput")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/typesInput";
    }

    @PostMapping("/types")
    public String post(@Validated Type type,BindingResult result, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/typesInput";
        }
        Type t = typeService.saveType(type);
        System.out.println(t.getId());
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }

        //因为返回的时候用的重定向，所以对重定向链接地址追加传递的参数用RedirectAttributes
        return "redirect:/admin/types";
    }

}
