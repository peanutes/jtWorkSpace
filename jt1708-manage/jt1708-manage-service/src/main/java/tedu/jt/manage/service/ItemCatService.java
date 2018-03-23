package tedu.jt.manage.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tedu.jt.common.service.RedisService;
import tedu.jt.manage.mapper.ItemCatMapper;
import tedu.jt.manage.vo.ItemCatData;
import tedu.jt.manage.vo.ItemCatResult;
import tedu.jt.mange.pojo.ItemCat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ItemCat业务逻辑层
 *
 * @author Administrator
 * @create 2017-12-10 9:13
 */
@Service
public class ItemCatService extends BaseService<ItemCat>{
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(ItemCatService.class);
    public List<ItemCat> queryItemCatList(Long parentId) {
        //判断缓存中是否有数据
        String ITEM_CAT_KEY = "ITEM_CAT_"+parentId;
        String jsonData = redisService.get(ITEM_CAT_KEY);
        if(StringUtils.isNotEmpty(jsonData)){
            //代表从缓存中查询到数据
            JsonNode jsonNode = null;
            try {
                jsonNode = MAPPER.readTree(jsonData);
                if (jsonNode.isArray() && jsonNode.size() > 0) {
                    return MAPPER.readValue(jsonNode.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
                }

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
            return null;
        }else {
            ItemCat itemCat = new ItemCat();
            itemCat.setStatus(1);
            itemCat.setParentId(parentId);
            List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
            //写缓存
            String value = null;
            try {
                value = MAPPER.writeValueAsString(itemCatList);
            } catch (JsonProcessingException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
            redisService.set(ITEM_CAT_KEY,value);
            return itemCatList;
        }

    }
    //形成商品分类菜单json结构
    public ItemCatResult getItemCatResult(){
        //获取所有数据
        List<ItemCat> itemCatList = super.queryAll();

        //1.获取当前节点下的所有的子节点
        Map<Long,List<ItemCat>> map = new HashedMap();
        for(ItemCat itemCat : itemCatList){
            Long parentId = itemCat.getParentId();
            if(!map.containsKey(parentId)){
                map.put(parentId,new ArrayList<ItemCat>());
            }
            map.get(parentId).add(itemCat);
        }
        //2.构建itemcatresult,只能pingjie
        ItemCatResult result = new ItemCatResult();
        //构建一级菜单
        String url = "";
        List<ItemCatData> list1 = new ArrayList<ItemCatData>();
        for(ItemCat itemCat1 : map.get(0L)){
            //一级菜单
            ItemCatData itemCatData1 = new ItemCatData();
            url = "products/"+itemCat1.getId()+".html";
            itemCatData1.setUrl(url);
            itemCatData1.setName("<a href=\""+url+"\">"+ itemCat1.getName()+"</a>");
            //构建二级菜单
            List<ItemCatData> list2 = new ArrayList<ItemCatData>();
            for (ItemCat itemCat2 : map.get(itemCat1.getId())){
                //二级菜单
                ItemCatData itemCatData2 = new ItemCatData();
                url = "/products"+itemCat2.getId()+".html";
                itemCatData2.setUrl(url);
                itemCatData2.setName(itemCat2.getName());
                //构建三级菜单
                List<String> list3 = new ArrayList<String>();
                for(ItemCat itemCat3 : map.get(itemCat2.getId())){
                    //三级菜单
                    url = "/products"+itemCat3.getId()+".html";
                    list3.add(url+"|"+itemCat3.getName());
                }

                itemCatData2.setItems(list3);
                list2.add(itemCatData2);
            }
            itemCatData1.setItems(list2);
            list1.add(itemCatData1);
            //只要14个菜单
            if(list1.size()>15){
                break;
            }
        }
        result.setItemCatData(list1);
        return result;
    }

}
