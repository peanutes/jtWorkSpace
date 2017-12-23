package tedu.jt.order.pojo;

import tedu.jt.common.po.BasePojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单物流
 *
 * @author Administrator
 * @create 2017-12-23 14:59
 */
@Table
public class OrderShipping extends BasePojo{
    @Id
    private String orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverMobile;
    private String receiverState;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverZip;

    public String getOrderId() {
        return orderId;
    }

    public OrderShipping setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public OrderShipping setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public OrderShipping setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
        return this;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public OrderShipping setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
        return this;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public OrderShipping setReceiverState(String receiverState) {
        this.receiverState = receiverState;
        return this;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public OrderShipping setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
        return this;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public OrderShipping setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public OrderShipping setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public OrderShipping setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
        return this;
    }
}
