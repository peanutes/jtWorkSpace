package tedu.jt.order.pojo;

import tedu.jt.common.po.BasePojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 *
 * @author Administrator
 * @create 2017-12-23 14:40
 */
@Table(name="tb_order")
public class Order extends BasePojo{
    //订单和订单商品：一对多
    List<OrderItem> orderItemList;
    //订单和物流 一对一
    OrderShipping orderShipping;
    @Id
    private String orderId;
    private String payment;
    private Integer paymentType;
    private String postFee;
    private Integer status;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;

    private String shippingName;
    private String shippingCode;
    private Long userId;

    private String buyerMessage;
    private String buyerNick;
    private String buyerRate;

    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Order setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public Order setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public Order setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getPostFee() {
        return postFee;
    }

    public Order setPostFee(String postFee) {
        this.postFee = postFee;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Order setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public Order setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public Order setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
        return this;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public Order setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getShippingName() {
        return shippingName;
    }

    public Order setShippingName(String shippingName) {
        this.shippingName = shippingName;
        return this;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public Order setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Order setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public Order setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
        return this;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public Order setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
        return this;
    }

    public String getBuyerRate() {
        return buyerRate;
    }

    public Order setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate;
        return this;
    }
}
