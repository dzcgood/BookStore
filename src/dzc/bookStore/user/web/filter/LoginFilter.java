package dzc.bookStore.user.web.filter;

import dzc.bookStore.user.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/FrontJsps/cart/*","/FrontJsps/order/*",
        "/FrontJsps/user/mod.jsp","/CartServlet","/OrderServlet"})
//"CartServlet","OrderServlet"
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /**从session获取用户信息，若存在用户信息，则放行；若没有，则保存错误信息，传发到login.jsp*/
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        User user = (User)httpRequest.getSession().getAttribute("session_user");
        if(user != null){
            chain.doFilter(request, response);
        }else{
            httpRequest.setAttribute("message","您还没有登陆");
            httpRequest.getRequestDispatcher("/FrontJsps/user/login.jsp").forward(httpRequest,response);
        }
    }
}
