package dzc.bookStore.cart.web.servlet;

import dzc.bookStore.book.domain.Book;
import dzc.bookStore.book.service.BookService;
import dzc.bookStore.cart.domain.Cart;
import dzc.bookStore.cart.domain.CartItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {
    /**添加条目*/
    public String add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**获取购物车*/
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        /**获取图书book_id查询数据库得到book*/
        String book_id = request.getParameter("book_id");
        Book book = new BookService().load(book_id);
        int count = Integer.parseInt(request.getParameter("count"));
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setBook(book);
        cart.add(cartItem);
        return "/FrontJsps/cart/list.jsp";
    }

    /**清除操作*/
    public String clear(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.clear();
        return "/FrontJsps/cart/list.jsp";
    }

    /**删除操作*/
    public String delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**获取购物车*/
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        /**获取图书book_id查询数据库得到book*/
        String book_id = request.getParameter("book_id");
        Book book = new BookService().load(book_id);
        cart.delete(book.getBook_id());
        return "/FrontJsps/cart/list.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("add"))
            route = add(request,response);
        else if(method.equals("delete"))
            route = delete(request,response);
        else if(method.equals("clear"))
            route = clear(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
