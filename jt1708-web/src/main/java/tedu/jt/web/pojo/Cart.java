package tedu.jt.web.pojo;

import tedu.jt.common.po.BasePojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 购物车实体类
 *
 * @author Administrator
 * @create 2017-12-22 12:24
 */

public class Cart extends BasePojo{
    private Long id;
    private Long userId;
    private Long itemId;
    private String itemTitle;
    private String itemImage;
    private Long itemPrice;
    private Integer num;

    public Long getId() {
        return id;
    }

    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Cart setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public Cart setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Cart setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        return this;
    }

    public String getItemImage() {
        return itemImage;
    }

    public Cart setItemImage(String itemImage) {
        this.itemImage = itemImage;
        return this;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public Cart setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;

    }
}
