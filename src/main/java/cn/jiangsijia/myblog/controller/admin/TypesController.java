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

    @GetMapping("/goAddType")
    public String input(Model model){
        System.out.println("跳转到新增");
        model.addAttribute("type", new Type());
        return "admin/typesInput";
    }

    @GetMapping("/selectType/{id}")
    public String editInput (@PathVariable Long id, Model model){
        System.out.println("跳转到编辑根据id查询=============="+id+"===================");
        Type type = typeService.getType(id);
        System.out.println( type.getId()+"================"+type.getName());
        model.addAttribute("type", type);
        return "admin/typesInput";
    }

    //分页查询
    @GetMapping("/types")
    public String types(@PageableDefault(size = 7,sort = {"id"},
            direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){

        Page<Type> listType = typeService.listType(pageable);
        model.addAttribute("page",listType);
        return "admin/types";
    }

    //删除
    @GetMapping("/deleteType/{id}")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/types";
    }

    //添加 @Validated在type传入时进行校验
    @PostMapping("/types")
    public String types(@Validated Type type,BindingResult result, RedirectAttributes attributes) {
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

    //修改
    @PostMapping("/types/{id}")
    public String updateTypes(@Validated Type type,BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/typesInput";
        }

        Type t = typeService.updateType(id,type);
        System.out.println(t.getId());
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        //因为返回的时候用的重定向，所以对重定向链接地址追加传递的参数用RedirectAttributes
        return "redirect:/admin/types";
    }

}
