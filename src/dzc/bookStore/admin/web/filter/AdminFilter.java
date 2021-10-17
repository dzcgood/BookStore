package dzc.bookStore.admin.web.filter;

import dzc.bookStore.admin.domain.Admin;
import dzc.bookStore.user.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "AdminFilter",urlPatterns = {"/AdminJsps/admin/book/*","/AdminJsps/admin/category/*",
        "/AdminJsps/admin/order/*","/AdminBookServlet","/AdminCategoryServlet","/AdminOrderServlet","/AdminJsps/admin/user/*",
        "/AdminUserServlet"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /**从session获取用户信息，若存在用户信息，则放行；若没有，则保存错误信息，传发到login.jsp*/
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        Admin admin = (Admin)httpRequest.getSession().getAttribute("session_admin");
        if(admin != null){
            chain.doFilter(request, response);
        }else {
            httpRequest.setAttribute("message", "您还没有登陆");
            httpRequest.getRequestDispatcher("/AdminJsps/login.jsp").forward(httpRequest, response);
        }
    }
}
