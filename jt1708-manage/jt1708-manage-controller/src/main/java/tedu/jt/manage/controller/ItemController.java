package tedu.jt.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.common.vo.EasyUIResult;
import tedu.jt.common.vo.SysResult;
import tedu.jt.manage.service.ItemService;
import tedu.jt.mange.pojo.Item;
import tedu.jt.mange.pojo.ItemDesc;


/**
 * item的控制跳转
 *
 * @author Administrator
 * @create 2017-12-10 10:51
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    private static final Logger log = Logger.getLogger(ItemController.class);
    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult queryItemList(Integer page,Integer rows){
        return itemService.queryItemList(page,rows);
    }
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(Item item,String desc){
        try {
            itemService.saveItem(item,desc);
            return SysResult.oK();
        }catch (Exception e){
            log.error(e.getMessage());
            return SysResult.build(201,"商品添加失败!");
        }
    }
    @RequestMapping("/query/item/desc/{itemId}")
    @ResponseBody
    public SysResult queryItemDesc(@PathVariable Long itemId){
        try{

            ItemDesc itemDesc=itemService.queryItemDesc(itemId);
            return SysResult.oK(itemDesc);

        }catch (Exception e){
            log.error(e.getMessage());
            return SysResult.build(201,"商品描述查询失败！");
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(Item item,String desc){
        try{
            itemService.updateItem(item,desc);
            return SysResult.oK();
        }catch (Exception e){
            log.error(e.getMessage());
            return SysResult.build(201,"商品修改失败!");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteItem(String[] ids){
        try{
            itemService.deleteItems(ids);
            return SysResult.oK();
        }catch (Exception e){
            log.error(e.getMessage());
            return SysResult.build(201,"商品删除失败！");
        }
    }
    @RequestMapping("/instock")
    @ResponseBody
    public SysResult instockItems(String[] ids){
        try{
            itemService.updateInstockItems(ids);
            return SysResult.oK();
        }catch (Exception e){
            log.error(e.getMessage());
            return SysResult.build(201,"商品下架失败！");
        }
    }
    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelfItems(String[] ids){
        try{
            itemService.updateReshelfItems(ids);
            return SysResult.oK();
        }catch (Exception e){
            log.error(e.getMessage());
            return SysResult.build(201,"商品上架失败！");
        }
    }
}
