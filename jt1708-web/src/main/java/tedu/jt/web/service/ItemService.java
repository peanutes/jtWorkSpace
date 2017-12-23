package tedu.jt.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.jt.common.service.HttpClientService;
import tedu.jt.common.service.RedisService;
import tedu.jt.web.pojo.Item;
import tedu.jt.web.pojo.ItemDesc;

/**
 * 前台商品业务逻辑
 *
 * @author Administrator
 * @create 2017-12-19 14:28
 */
@Service
public class ItemService {
    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private RedisService redisService;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Item getItemId(Long id) throws Exception {
        String ITEM_KEY = "ITEM_"+id;
        String jsonData = redisService.get(ITEM_KEY);
        Item item = null;
        if(StringUtils.isNotEmpty(jsonData)){
            item = MAPPER.readValue(jsonData,Item.class);
        }else {
            //通过HttpClient发起http请求
            String url = "http://manage.jt.com/items/"+id;
            jsonData = httpClientService.doGet(url);
            item = MAPPER.readValue(jsonData,Item.class);
            jsonData =MAPPER.writeValueAsString(item);
            redisService.set(ITEM_KEY,jsonData);
        }
        return item;
    }
    public ItemDesc getItemDescById(Long id) throws Exception {
        String ITEM_DESC_KEY = "ITEM_DESC"+id;
        String jsonData = redisService.get(ITEM_DESC_KEY);
        ItemDesc itemDesc = null;
        if(StringUtils.isNotEmpty(jsonData)){
            itemDesc = MAPPER.readValue(jsonData,ItemDesc.class);
        }else {
            String url = "http://manage.jt.com/items/desc/"+id;
            jsonData = httpClientService.doGet(url);
            itemDesc = MAPPER.readValue(jsonData,ItemDesc.class);
            jsonData = MAPPER.writeValueAsString(itemDesc);
            redisService.set(ITEM_DESC_KEY,jsonData);
        }

        return itemDesc;
    }

}
