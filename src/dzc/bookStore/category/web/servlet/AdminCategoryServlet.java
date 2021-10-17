package dzc.bookStore.category.web.servlet;

import cn.itcast.commons.CommonUtils;
import dzc.bookStore.category.domain.Category;
import dzc.bookStore.category.service.CategoryException;
import dzc.bookStore.category.service.CategoryService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/AdminCategoryServlet")
public class AdminCategoryServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**调用service方法得到所有分类并保存到request域*/
        request.setAttribute("categoryList",categoryService.findAll());
        return "/AdminJsps/admin/category/list.jsp";
    }

    /**
     * 添加分类
     * */
    public String add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**封装表单数据*/
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        /**补全category_id*/
        category.setCategory_id(CommonUtils.uuid());
        /**调用categoryService.add方法*/
        categoryService.add(category);
        request.setAttribute("message","添加分类成功！");
        return "/AdminJsps/admin/category/add.jsp";
    }

    /**
     * 删除分类
     * */
    public String delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**获取category_id*/
        String category_id = request.getParameter("category_id");
        /**调用categoryService.delete方法*/
        try{
            categoryService.delete(category_id);
            return findAll(request,response);
        }catch (CategoryException e) {
            request.setAttribute("message", e.getMessage());
            return "/AdminJsps/message.jsp";
        }
    }

    /**
     * 修改分类之前的加载工作
     * */
    public String editPre(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String category_id = request.getParameter("category_id");
        request.setAttribute("category",categoryService.load(category_id));
        return "/AdminJsps/admin/category/mod.jsp";
    }

    /**
     * 修改分类
     * */
    public String edit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**封装表单数据*/
        Category category = CommonUtils.toBean(request.getParameterMap(),Category.class);
        /**调用service完成修改工作*/
        categoryService.edit(category);
        return findAll(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("findAll"))
            route = findAll(request,response);
        else if(method.equals("add"))
            route = add(request,response);
        else if(method.equals("delete"))
            route = delete(request,response);
        else if(method.equals("editPre"))
            route = editPre(request,response);
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

