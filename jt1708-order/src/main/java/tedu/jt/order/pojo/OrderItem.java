package tedu.jt.order.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订单商品信息
 *
 * @author Administrator
 * @create 2017-12-23 14:49
 */
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
    @Id
    private String itemId;
    @Id
    private String orderId;
    private String title;
    private Integer num;
    private Long price;
    private String totalFee;
    private String picPath;

    public String getItemId() {
        return itemId;
    }

    public OrderItem setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderItem setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OrderItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public OrderItem setNum(Integer num) {
        this.num = num;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public OrderItem setPrice(Long price) {
        this.price = price;
        return this;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public OrderItem setTotalFee(String totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getPicPath() {
        return picPath;
    }

    public OrderItem setPicPath(String picPath) {
        this.picPath = picPath;
        return this;
    }
}
