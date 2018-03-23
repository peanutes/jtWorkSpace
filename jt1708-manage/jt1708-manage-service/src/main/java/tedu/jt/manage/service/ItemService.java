package tedu.jt.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.jt.common.vo.EasyUIResult;
import tedu.jt.common.vo.SysResult;
import tedu.jt.manage.mapper.ItemDescMapper;
import tedu.jt.manage.mapper.ItemMapper;
import tedu.jt.mange.pojo.Item;
import tedu.jt.mange.pojo.ItemDesc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Item的业务逻辑层
 *
 * @author Administrator
 * @create 2017-12-10 10:37
 */
@Service
public class ItemService extends BaseService<Item>{
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     * 查询商品并分页
     * @param page 当前页数
     * @param rows 当前页数的商品总数量
     * @return
     */
    public EasyUIResult queryItemList(Integer page,Integer rows){
        PageHelper.startPage(page,rows);
        List<Item> itemList = itemMapper.queryItemList();
        PageInfo pageInfo = new PageInfo(itemList);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }
    public ItemDesc queryItemDesc(Long itemId){
       return itemDescMapper.selectByPrimaryKey(itemId);
    }

    /**
     * 新增商品及商品描述
     * @param item
     * @param desc
     */
    public void saveItem(Item item,String desc){

        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insertSelective(item);
        //新增商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getUpdated());
        itemDescMapper.insert(itemDesc);
    }

    /**
     * 修改商品及商品描述
     * @param item
     * @param desc
     */

    public void updateItem(Item item, String desc){
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
        //修改商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(item.getUpdated());
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);

    }

    /** 级联删除
     * 批量删除商品
     * @param ids
     */
    public void deleteItems(String[] ids){
        itemMapper.deleteItems(ids);
        itemDescMapper.deleteByIDS(ids);
    }

    /**
     * 批量下架商品
     * @param ids
     */
    public void updateInstockItems(String[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status",2);
        map.put("ids",ids);
        itemMapper.instockItems(map);
    }

    /**
     * 批量上架商品
     * @param ids
     */
    public void updateReshelfItems(String[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status",1);
        map.put("ids",ids);
        itemMapper.reshelfItems(map);
    }

    /**
     * 根据商品id查询商品描述
     * @param itemId
     * @return
     */
    public ItemDesc queryItemDescById(Long itemId){
        ItemDesc itemDesc =itemDescMapper.selectByPrimaryKey(itemId)
        return itemDesc;
    }
}
