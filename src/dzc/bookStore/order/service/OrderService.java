package dzc.bookStore.order.service;

import cn.itcast.jdbc.JdbcUtils;
import dzc.bookStore.order.dao.OrderDao;
import dzc.bookStore.order.domain.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public void add(Order order){
        try{
            //开始事务
            JdbcUtils.beginTransaction();
            /**插入订单*/
            orderDao.addOrder(order);
            /**插入订单中的条目*/
            orderDao.addOrderItemList(order.getOrderItemList());
            //提交事物
            JdbcUtils.commitTransaction();
        }catch (Exception e){
            //回滚事物
            try{
                JdbcUtils.rollbackTransaction();
            }catch (SQLException e1) {
            }
            throw new RuntimeException(e);
        }
    }

    public List<Order> myOrders(String userid){
        return orderDao.findByUser_id(userid);
    }

    /**
     * 加载订单
     * */
    public Order load(String order_id){
        return orderDao.load(order_id);
    }

    /**
     * 模拟付款
     * */
    public void pay(String order_id,String address){
        orderDao.updateState(order_id,2);/**更新订单状态为2*/
        orderDao.updateAddress(order_id,address); /**更新订单收获地址*/
    }

    /**
     * 确认收货
     * */
    public void confirm(String order_id)throws OrderException{
        int order_state = orderDao.getStateByOrder_id(order_id);
        if(order_state!=3)throw new OrderException("清单确认失败，请先付款！");
        orderDao.updateState(order_id,4);/**交易完成*/
    }

    /**
     * 发货
     * */
    public void send(String order_id){
        orderDao.updateState(order_id,3);/**修改订单状态为3表示发货*/
    }

    /**
     * 查询所有订单
     * */
    public List<Order> findAll(){
        return orderDao.findAll();
    }

    /**
     * 按照订单状态查询订单
     * */
    public List<Order> getOrdersByOrder_State(int order_state){
        return orderDao.getOrdersByOrder_State(order_state);
    }
}
