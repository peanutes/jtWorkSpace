package tedu.jt.web.pojo;

import tedu.jt.common.po.BasePojo;

import javax.persistence.Id;
import javax.persistence.Table;

/** 商品描述表
 * @author Administrator
 * @create 2017-12-11 14:25
 */
public class ItemDesc extends BasePojo{
    private Long itemId;
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public ItemDesc setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public ItemDesc setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }
}
