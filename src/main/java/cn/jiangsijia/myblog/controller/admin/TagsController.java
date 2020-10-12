package cn.jiangsijia.myblog.controller.admin;

import cn.jiangsijia.myblog.po.Tag;
import cn.jiangsijia.myblog.po.Type;
import cn.jiangsijia.myblog.service.TagsService;
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
 * @date 2020/6/16 0016.
 */
@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagsService tagsService;

    //分页查询
    @GetMapping("/tags")
    public String types(@PageableDefault(size = 7,sort = {"id"},
            direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){

        Page<Tag> listTags = tagsService.listTags(pageable);
        model.addAttribute("page",listTags);
        return "admin/tags";
    }

    //删除
    @GetMapping("/deleteTag/{id}")
    public String deleteTag (@PathVariable Long id, RedirectAttributes attributes){
        tagsService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/tags";
    }

    //转跳到添加
    @GetMapping("/goAddTag")
    public String input(Model model){
        System.out.println("跳转到新增");
        model.addAttribute("tag", new Tag());
        return "admin/tagsInput";
    }

    //添加 @Validated在type传入时进行校验
    @PostMapping("/tag")
    public String tag(@Validated Tag tag,BindingResult result, RedirectAttributes attributes) {

       Tag tag1 = tagsService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tagsInput";
        }
        Tag t = tagsService.saveTag(tag);
        //System.out.println(t.getId());
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }

        //因为返回的时候用的重定向，所以对重定向链接地址追加传递的参数用RedirectAttributes
        return "redirect:/admin/tags";
    }



    //修改
    @PostMapping("/tag/{id}")
    public String updateTypes(@Validated Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {

        Tag tag1 = tagsService.getTagByName(tag.getName());

        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tagsInput";
        }

        Tag t = tagsService.updateTag(id, tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        //因为返回的时候用的重定向，所以对重定向链接地址追加传递的参数用RedirectAttributes
        return "redirect:/admin/tags";
    }

}
