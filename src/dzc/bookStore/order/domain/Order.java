package dzc.bookStore.order.domain;

import dzc.bookStore.user.domain.User;

import java.util.Date;
import java.util.List;

public class Order {
    private String order_id;
    private Date order_time;/**下单时间*/
    private double order_total;/**合计*/
    private int order_state;/**一共四种状态 1未付款，2 已付款未发货 3 已发货未收获 4 已确认收款*/
    private User owner;/**订单所有者*/
    private String order_address;/**收货地址*/
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    /**当前订单下所有条目*/

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", order_time=" + order_time +
                ", order_total=" + order_total +
                ", order_state=" + order_state +
                ", owner=" + owner +
                ", order_address='" + order_address + '\'' +
                '}';
    }
}
