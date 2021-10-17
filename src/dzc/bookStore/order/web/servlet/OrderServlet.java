package dzc.bookStore.order.web.servlet;

import cn.itcast.commons.CommonUtils;
import dzc.bookStore.book.domain.Book;
import dzc.bookStore.book.service.BookService;
import dzc.bookStore.cart.domain.Cart;
import dzc.bookStore.cart.domain.CartItem;
import dzc.bookStore.order.domain.Order;
import dzc.bookStore.order.domain.OrderItem;
import dzc.bookStore.order.service.OrderException;
import dzc.bookStore.order.service.OrderService;
import dzc.bookStore.user.domain.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    OrderService orderService = new OrderService();

    /**
     * 添加订单
     * 从session取出cart用于生成order
     * */
    public String add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**从session获取cart*/
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        /**把cart转换成order*/
        Order order = new Order();
        order.setOrder_id(CommonUtils.uuid());
        order.setOrder_time(new Date());
        order.setOrder_state(1);/**状态为1表示未付款*/
        User user = (User)request.getSession().getAttribute("session_user");
        order.setOwner(user);
        order.setOrder_total(cart.getTotal());
        /**创建订单条目集合*/
        List<OrderItem> orderItemList  = new ArrayList<OrderItem>();
        for(CartItem cartItem:cart.getCollection()){
            OrderItem oi = new OrderItem();
            oi.setItem_id(CommonUtils.uuid());
            oi.setItem_count(cartItem.getCount());
            oi.setBook(cartItem.getBook());
            oi.setItem_subtotal(cartItem.getSubtotal());
            oi.setOrder(order);
            orderItemList.add(oi);
        }
        order.setOrderItemList(orderItemList);
        /**使用orderService添加订单*/
        cart.clear();
        orderService.add(order);
        request.setAttribute("order",order);
        return "/FrontJsps/order/orderInfo.jsp";
    }

    /**
     * 我的订单
     * */
    public String myOrders(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**从session得到当前用户，获取userid*/
        /**使用user_id调用orderServlet的myOrders()得到该用户的所有订单List<order>*/
        /**把订单列表保存到request中，转发到/FrontJsps/order/list.jsp*/
        User user = (User)request.getSession().getAttribute("session_user");
        List<Order> orderList = orderService.myOrders(user.getUserid());
        request.setAttribute("orderList",orderList);
        return "/FrontJsps/order/list.jsp";
    }

    /**
     * 加载订单内容
     * */
    public String load(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**得到order_id*/
        /**用order_id调用service得到order*/
        request.setAttribute("order",orderService.load(request.getParameter("order_id")));
        return "/FrontJsps/order/orderInfo.jsp";
    }

    /**
     * 确认收货
     * */
    public String confirm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String order_id = request.getParameter("order_id");
        try{
            orderService.confirm(order_id);
            request.setAttribute("message","交易成功！");
        }catch(OrderException e){
            request.setAttribute("message",e.getMessage());
        }
        return "/FrontJsps/message.jsp";
    }

    /**
     * 付款功能
     * 模拟付款，将order_state 改成2，order_address
     * */
    public String pay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String order_id = request.getParameter("order_id");
        String address = request.getParameter("address");
        if(address.trim().equals(""))
            address="广东省广州市华南师范大学计算机学院邓智超收";
        orderService.pay(order_id,address);
        request.setAttribute("message","支付成功！等待卖家发货~");
        return "/FrontJsps/message.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("add"))
            route = add(request,response);
        else if(method.equals("myOrders"))
            route = myOrders(request,response);
        else if(method.equals("load"))
            route = load(request,response);
        else if(method.equals("confirm"))
            route = confirm(request,response);
        else if(method.equals("pay"))
            route = pay(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
