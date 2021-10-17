package dzc.bookStore.book.web.servlet;

import dzc.bookStore.book.service.BookService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/BookServlet")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    /**
     * 查询所有图书
     * */
    public String findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("bookList",bookService.findAll());
        return "/FrontJsps/book/list.jsp";
    }

    /**
     * 按分类查询图书
     * */
    public String findByCategory(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String category_id =request.getParameter("category_id");
        request.setAttribute("bookList",bookService.findByCategory(category_id));
        return "/FrontJsps/book/list.jsp";
    }

    /**
     * 加载图书
     * */
    public String load(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**获取book_id*/
        /**查询得到book*/
        request.setAttribute("book",bookService.load(request.getParameter("book_id")));
        return "/FrontJsps/book/bookInfo.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("findAll"))
            route = findAll(request,response);
        else if(method.equals("findByCategory"))
            route = findByCategory(request,response);
        else if(method.equals("load"))
            route = load(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
