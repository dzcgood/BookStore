package dzc.bookStore.order.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import dzc.bookStore.book.domain.Book;
import dzc.bookStore.order.domain.Order;
import dzc.bookStore.order.domain.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private QueryRunner qr = new TxQueryRunner();
    /**
     * 添加订单
     * */
    public void addOrder(Order order){
        try{
            String sql="insert into orders values(?,?,?,?,?,?)";
            /**util的date转换成sql的timestamp*/
            Timestamp timestamp = new Timestamp(order.getOrder_time().getTime());
            Object[] params = {order.getOrder_id(),timestamp,order.getOrder_total(),
                    order.getOrder_state(),order.getOwner().getUserid(),order.getOrder_address()};
            qr.update(sql,params);
        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入订单条目
     * */
    public void addOrderItemList(List<OrderItem>orderItemList){
        try{
            String sql ="insert into orderitem values(?,?,?,?,?)";
            /**把orderItemList转成二维数组*/
            Object[][]params=new Object[orderItemList.size()][];
            /**循环遍历orderItemList，并赋值*/
            for(int i=0;i<orderItemList.size();i++){
                OrderItem item=orderItemList.get(i);
                params[i]=new Object[]{item.getItem_id(),item.getItem_count(),
                        item.getItem_subtotal(),item.getOrder().getOrder_id(),item.getBook().getBook_id()};
            }
            /**执行批处理*/
            qr.batch(sql,params);
        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    /**
     * 查找所有订单
     * */
    public List<Order> findAll(){
        List<Order>orderList;
        /**找到用户的所有order*/
        try{
            String sql = "select * from orders";
            orderList = qr.query(sql,new BeanListHandler<Order>(Order.class));
            /**循环每个order，加载所有orderItems*/
            for(Order order : orderList){
                loadOrderItems(order);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return orderList;
    }

    /**
     * 按照订单状态查找订单
     * */
    public List<Order> getOrdersByOrder_State(int order_state){
        List<Order>orderList;
        /**通过user_id找到用户的所有order*/
        try{
            String sql = "select * from orders where order_state = ?";
            orderList = qr.query(sql,new BeanListHandler<Order>(Order.class),order_state);
            /**循环每个order，加载所有orderItems*/
            for(Order order : orderList){
                loadOrderItems(order);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return orderList;
    }

    /**
     * 按照user_id查询
     * */
    public List<Order> findByUser_id(String userid){
        List<Order>orderList;
        /**通过user_id找到用户的所有order*/
        try{
            String sql = "select * from orders where userid = ?";
            orderList = qr.query(sql,new BeanListHandler<Order>(Order.class),userid);
            /**循环每个order，加载所有orderItems*/
            for(Order order : orderList){
                loadOrderItems(order);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return orderList;
    }

    private void loadOrderItems(Order order) throws SQLException{
        /**
         * 一行结果集包括两个bean的内容
         * 查询orderItem和book表*/
        String sql = "select * from orderItem i,book b where i.book_id = b.book_id and order_id = ?";
        /**每个map对应一个结果集*/
        List<Map<String,Object>> mapList = qr.query(sql,new MapListHandler(),order.getOrder_id());
        /**使用Map建立orderItem和Book对象的关系*/
        List<OrderItem> orderItemList = toOrderItemList(mapList);
        order.setOrderItemList(orderItemList);
    }

    /**
     * 把mapList中每个map转成两个对象，并建立联系
     * */

    public List<OrderItem> toOrderItemList(List<Map<String,Object>>mapList){
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(Map<String,Object> map :mapList){
            OrderItem item = toOrderItem(map);
            orderItemList.add(item);
        }
        return orderItemList;
    }

    /**
     * 把一个map转换成OrderItem对象
     * */
    private OrderItem toOrderItem(Map<String,Object>map){
        OrderItem orderItem = CommonUtils.toBean(map,OrderItem.class);
        Book book= CommonUtils.toBean(map,Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    public Order load(String order_id){
        Order order;
        /**通过user_id找到用户的所有order*/
        try{
            String sql = "select * from orders where order_id = ?";
            order = qr.query(sql,new BeanHandler<Order>(Order.class),order_id);
            loadOrderItems(order);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return order;
    }

    /**
     * 查询订单状态
     * */
    public int getStateByOrder_id(String order_id){
        short num;
        try{
            String sql = "select order_state from orders where order_id = ?";
            num = (short)qr.query(sql,new ScalarHandler(),order_id);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return num;
    }

    /**
     * 修改订单状态
     * */
    public void updateState(String order_id,int order_state){
        try{
            String sql = "update orders set order_state = ? where order_id = ?";
            qr.update(sql,order_state,order_id);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改订单收货地址
     * */
    public void updateAddress(String order_id,String order_address){
        try{
            String sql = "update orders set order_address = ? where order_id = ?";
            qr.update(sql,order_address,order_id);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}




