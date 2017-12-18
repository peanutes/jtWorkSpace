package tedu.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.manage.service.ItemCatService;
import tedu.jt.mange.pojo.ItemCat;

import java.util.List;

/**
 * itemCat跳转控制类
 *
 * @author Administrator
 * @create 2017-12-10 9:31
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/list")
    @ResponseBody
    public List<ItemCat> queryItemCatList(@RequestParam(defaultValue = "0") Long id){

        List<ItemCat> list = itemCatService.queryItemCatList(id);
        return list;
    }


}
