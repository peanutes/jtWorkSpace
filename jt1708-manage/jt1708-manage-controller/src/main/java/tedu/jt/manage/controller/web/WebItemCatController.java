package tedu.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.manage.service.ItemCatService;
import tedu.jt.manage.vo.ItemCatResult;

/**
 * 前台菜单控制跳转
 *
 * @author Administrator
 * @create 2017-12-17 15:58
 */
@Controller
public class WebItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    //为前台服务
    @RequestMapping("/web/itemcat/all")
    @ResponseBody
    public ItemCatResult getItemCatResult(){
        return itemCatService.getItemCatResult();
    }
}
