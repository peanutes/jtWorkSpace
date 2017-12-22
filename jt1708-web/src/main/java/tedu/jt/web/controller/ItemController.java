package tedu.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tedu.jt.web.pojo.Item;
import tedu.jt.web.pojo.ItemDesc;
import tedu.jt.web.service.ItemService;

/**
 * 前台商品控制跳转
 *
 * @author Administrator
 * @create 2017-12-19 14:23
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/items/{itemId}")
    public String getItemId(@PathVariable Long itemId, Model model) throws Exception {
        Item item = itemService.getItemId(itemId);
        model.addAttribute("item",item);
        ItemDesc itemDesc = itemService.getItemDescById(itemId);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
