package tedu.jt.manage.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 商品分类菜单整体结构
 *
 * @author Administrator
 * @create 2017-12-17 14:54
 */
public class ItemCatResult {
    @JsonProperty(value = "data")
   private List<ItemCatData> itemCatData;

    public List<ItemCatData> getItemCatData() {
        return itemCatData;
    }

    public ItemCatResult setItemCatData(List<ItemCatData> itemCatData) {
        this.itemCatData = itemCatData;
        return this;
    }
}
