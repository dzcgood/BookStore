package dzc.bookStore.book.web.servlet.admin;

import dzc.bookStore.book.service.BookService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload2.FileItem;
import org.apache.commons.fileupload2.FileUploadException;
import org.apache.commons.fileupload2.disk.DiskFileItemFactory;
import org.apache.commons.fileupload2.servlet.ServletFileUpload;


import java.io.IOException;
import java.util.List;


/**作用是完成数据上传*/
@WebServlet(urlPatterns = "/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /**
         * 1、把表单数据封装到Book对象
         * */
        /**创建工厂*/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        /**得到解析器*/
        ServletFileUpload sfu = new ServletFileUpload(factory);
        /**使用解析器解析request对象，得到List<FileItem>*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
