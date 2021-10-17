package dzc.bookStore.category.web.servlet;

import dzc.bookStore.category.service.CategoryService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("categoryList",categoryService.findAll());
        return "/FrontJsps/left.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("findAll"))
            route = findAll(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
