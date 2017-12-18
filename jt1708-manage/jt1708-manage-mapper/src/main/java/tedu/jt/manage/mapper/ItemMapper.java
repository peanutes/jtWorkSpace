package tedu.jt.manage.mapper;

import tedu.jt.common.mapper.SysMapper;
import tedu.jt.mange.pojo.Item;

import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author Administrator
 * @create 2017-12-10 10:24
 */
public interface ItemMapper extends SysMapper<Item> {

    public List<Item> queryItemList();
    public void deleteItems(String[] ids);
    public void instockItems(Map<String,Object> map);
    public void reshelfItems(Map<String,Object> map);
}
