package dzc.bookStore.book.web.servlet.admin;

import cn.itcast.commons.CommonUtils;
import dzc.bookStore.book.domain.Book;
import dzc.bookStore.book.service.BookService;
import dzc.bookStore.category.domain.Category;
import dzc.bookStore.category.service.CategoryService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/AdminBookServlet")
public class AdminBookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();

    /**
     * 查看所有图书
     * */
    public String findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**调用service方法得到所有分类并保存到request域*/
        request.setAttribute("bookList",bookService.findAll());
        return "/AdminJsps/admin/book/list.jsp";
    }

    /**
     * 加载图书
     * */
    public String load(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**获取book_id,查询得到book,保存到request
         * 获取所有分类，保存到request
         * */
        request.setAttribute("book",bookService.load(request.getParameter("book_id")));
        request.setAttribute("categoryList",categoryService.findAll());
        return "/AdminJsps/admin/book/bookInfo.jsp";
    }

    /**
     * 添加图书之前准备工作
     * */
    public String addPre(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        /**查询所有分类，保存到request，转发到add.jsp,add.jsp把所有分类都用下拉列表显示*/
        request.setAttribute("categoryList",categoryService.findAll());
        return "/AdminJsps/admin/book/add.jsp";
    }

    /**
     * 删除图书
     * */
    public String delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**获取要删除的book_id*/
        String book_id = request.getParameter("book_id");
        bookService.delete(book_id);
        return findAll(request,response);
    }

    /**
     * 修改图书信息
     * */
    public String edit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        book.setCategory(category);
        bookService.edit(book);
        return findAll(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("findAll"))
            route = findAll(request,response);
        else if(method.equals("load"))
            route = load(request,response);
        else if(method.equals("addPre"))
            route = addPre(request,response);
        else if(method.equals("delete"))
            route = delete(request,response);
        else if(method.equals("edit"))
            route = edit(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
