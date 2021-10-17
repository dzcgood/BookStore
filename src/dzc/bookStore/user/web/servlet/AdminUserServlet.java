package dzc.bookStore.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import dzc.bookStore.user.domain.User;
import dzc.bookStore.user.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    /**
     * 查找所有用户功能
     * */
    public String findAll(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        /**调用service方法得到所有分类并保存到request域*/
        request.setAttribute("userList",userService.findAll());
        return "/AdminJsps/admin/user/list.jsp";
    }

    /**
     * 修改分类之前的加载工作
     * */
    public String editPre(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String userid = request.getParameter("userid");
        request.setAttribute("user",userService.load(userid));
        return "/AdminJsps/admin/user/mod.jsp";
    }

    /**
     * 修改分类
     * */
    public String edit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**封装表单数据*/
        User user = CommonUtils.toBean(request.getParameterMap(),User.class);
        /**调用service完成修改工作*/
        userService.edit(user);
        return findAll(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("findAll"))
            route = findAll(request,response);
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
