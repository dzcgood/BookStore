package dzc.bookStore.admin.web.servlet;

import cn.itcast.commons.CommonUtils;
import dzc.bookStore.admin.domain.Admin;
import dzc.bookStore.admin.service.AdminException;
import dzc.bookStore.admin.service.AdminService;
import dzc.bookStore.cart.domain.Cart;
import dzc.bookStore.user.domain.User;
import dzc.bookStore.user.service.UserException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    private AdminService adminService = new AdminService();

    /**
     * 管理员登录功能
     * */
    public String login(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        /**封装表单信息到form*/
        Admin form = CommonUtils.toBean(request.getParameterMap(),Admin.class);
        /**输入校验*/
        Map<String,String> errors = new HashMap<String,String>();
        String admin_name=form.getAdmin_name();
        if(admin_name==null||admin_name.trim().isEmpty()){
            errors.put("username","用户名不能为空！");
        }
        else if(admin_name.length()<3||admin_name.length()>10){
            errors.put("username","用户名长度必须在3~10之间！");
        }
        String admin_password=form.getAdmin_password();
        if(admin_password==null||admin_password.trim().isEmpty()){
            errors.put("user_password","密码不能为空！");
        }
        if(errors.size()>0){
            /**保存错误信息，保存表单信息，转发到login.jsp*/
            request.setAttribute("errors",errors);
            request.setAttribute("form",form);
            return "/AdminJsps/login.jsp";
        }
        /**调用service完成登录*/
        try{
            Admin admin = adminService.login(form);
            request.getSession().setAttribute("session_admin",admin);
            return "/AdminJsps/admin/main.jsp";
        }catch (AdminException e){
            request.setAttribute("message",e.getMessage());
            request.setAttribute("form",form);
            return "/AdminJsps/login.jsp";
        }
    }

    /**
     * 退出功能
     * */
    public String quit(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        request.getSession().invalidate();
        return "/AdminJsps/admin/main.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("login"))
            route = login(request,response);
        else if(method.equals("quit"))
            route = quit(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
