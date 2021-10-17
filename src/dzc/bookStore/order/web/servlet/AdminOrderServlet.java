package dzc.bookStore.order.web.servlet;

import dzc.bookStore.order.domain.Order;
import dzc.bookStore.order.service.OrderService;
import dzc.bookStore.user.domain.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    /**
     * 发货功能
     * */
    public String send(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String order_id = request.getParameter("order_id");
        orderService.send(order_id);
        return findAll(request,response);
    }


    /**
     * 按照订单状态获取订单
     * */
    public String getOrdersByOrder_State(HttpServletRequest request, HttpServletResponse response, int order_state)
            throws ServletException, IOException{
        List<Order>orderList;
        orderList = orderService.getOrdersByOrder_State(order_state);
        request.setAttribute("orderList",orderList);
        return "/AdminJsps/admin/order/list.jsp";
    }

    /**
     * 获取所有订单
     * */
    public String findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Order>orderList;
        orderList = orderService.findAll();
        request.setAttribute("orderList",orderList);
        return "/AdminJsps/admin/order/list.jsp";
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("send"))
            route = send(request,response);
        else if(method.equals("findAll"))
            route = findAll(request,response);
        else
            route = getOrdersByOrder_State(request,response,Integer.parseInt(request.getParameter("order_state")));
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
