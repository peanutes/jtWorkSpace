package tedu.jt.manage.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.common.service.RedisService;
import tedu.jt.manage.service.ItemService;
import tedu.jt.mange.pojo.Item;
import tedu.jt.mange.pojo.ItemDesc;

import java.io.IOException;

/**
 * 商品控制跳转
 *
 * @author Administrator
 * @create 2017-12-19 14:16
 */
@Controller
public class WebItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private RedisService redisService;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    //根据商品id获取当前商品
    @RequestMapping("/items/{itemId}")
    @ResponseBody
    public Item getItemId(@PathVariable Long itemId) throws IOException {
        String ITEM_KEY = "ITEM_"+itemId;
        String jsonData = redisService.get(ITEM_KEY);
        Item item =null;
        if(StringUtils.isNotEmpty(jsonData)){
            item = MAPPER.readValue(jsonData,Item.class);
        }else {
            item = itemService.queryById(itemId);
            redisService.set(ITEM_KEY,MAPPER.writeValueAsString(item));
        }


        return item;
    }
    //根据商品id查询商品描述
    @RequestMapping("/items/desc/{itemId}")
    @ResponseBody
    public ItemDesc queryItemDescById(@PathVariable Long itemId) throws IOException {
        String ITEM_DESC_KEY = "ITEM_DESC_"+itemId;
        String jsonData = redisService.get(ITEM_DESC_KEY);
        ItemDesc itemDesc = null;
        if(StringUtils.isNotEmpty(jsonData)){
            itemDesc = MAPPER.readValue(jsonData,ItemDesc.class);
        }else {
            itemDesc = itemService.queryItemDescById(itemId);
            redisService.set(ITEM_DESC_KEY,MAPPER.writeValueAsString(itemDesc));
        }
        return itemDesc;
    }
}
