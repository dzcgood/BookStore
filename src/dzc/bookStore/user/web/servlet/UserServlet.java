package dzc.bookStore.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import dzc.bookStore.cart.domain.Cart;
import dzc.bookStore.category.domain.Category;
import dzc.bookStore.user.domain.User;
import dzc.bookStore.user.service.UserException;
import dzc.bookStore.user.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    /**激活账号的函数*/
    public String activate(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        /**获取激活码*/
         String user_code = request.getParameter("user_code");
        /**调用service方法完成激活*/
         try{
             userService.active(user_code);
             request.setAttribute("message","恭喜，激活成功");
         }catch(UserException e)
         {
             request.setAttribute("message",e.getMessage());
         }
        return "/FrontJsps/message.jsp";
    }
    /**
     * 注册功能
     * 1、封装表单数据到form对象
     * 2、补全user_id和user_code
     * 3、输入校验
     *  >保存错误信息并转发到register.jsp
     * 4、调用UserService.register()完成注册
     *  >保存错误信息并转发到register.jsp
     * 5、发邮件
     * 6、保存成功信息到message.jsp
     * */
    public String regist(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        /**封装表单数据*/
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        /**补全user_id和user_code*/
        form.setUserid(CommonUtils.uuid());
        form.setUser_code(CommonUtils.uuid()+CommonUtils.uuid());//32位激活码
        /**输入校验
         * 1、创建一个Map用来封装错误信息，其中key为表单字段名称，值为错误信息
         * 2、获取form中的username，user_email,user_servlet，并开始校验
         * 3、判断是否存在校验信息
         * */
        Map<String,String> errors = new HashMap<String,String>();
        /**校验username*/
        String username=form.getUsername();
        if(username==null||username.trim().isEmpty()){
            errors.put("username","用户名不能为空！");
        }
        else if(username.length()<3||username.length()>10){
            errors.put("username","用户名长度必须在3~10之间！");
        }
        /**校验user_email*/
        String user_email=form.getUser_email();
        if(user_email==null||user_email.trim().isEmpty()){
            errors.put("user_email","Email不能为空！");
        }
        else if(!user_email.matches("\\w+@\\w+\\.\\w+")){/**正则表达式  邮箱格式为xxxxxx@xxx.xxx*/
            errors.put("user_email","Email格式错误！");
        }
        /**校验user_password*/
        String user_password=form.getUser_password();
        if(user_password==null||username.trim().isEmpty()){
            errors.put("user_password","密码不能为空！");
        }
        else if(user_password.length()<6||user_password.length()>12){
            errors.put("user_password","密码长度必须在6~12之间！");
        }
        /**判断是否存在错误信息*/
        if(errors.size()>0){
            /**保存错误信息，保存表单信息，转发到register.jsp*/
            request.setAttribute("errors",errors);
            request.setAttribute("form",form);
            return "/FrontJsps/user/register.jsp";
        }
        /**调用UserService.register()方法完成注册*/
        try{
            userService.regist(form);
        }catch(UserException e){
            /**保存异常信息，保存表单信息，转发到register.jsp*/
            request.setAttribute("message",e.getMessage());
            request.setAttribute("form",form);
            return "/FrontJsps/user/register.jsp";
        }
        /**发邮件*/
        /**获取配置文件内容*/
        Properties props=new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
        String host=props.getProperty("host");
        String uname= props.getProperty("uname");
        String password= props.getProperty("password");
        String from= props.getProperty("from");
        String to= form.getUser_email();
        String subject= props.getProperty("subject");
        String content=props.getProperty("content");
        /**替换占位符*/
        content= MessageFormat.format(content,form.getUser_code());
        Session session= MailUtils.createSession(host,uname,password);
        Mail mail =new Mail(from,to,subject,content);
        try{
            MailUtils.send(session,mail);//发邮件
        }catch (MessagingException e){
        }
        /**保存成功信息，转发到message.jsp*/
        request.setAttribute("message","恭喜注册成功，请到邮箱激活！");
        return "/FrontJsps/message.jsp";
    }

    /**
     * 登录功能
     * */
    public String login(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        /**封装表单信息到form*/
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        /**输入校验*/
        Map<String,String> errors = new HashMap<String,String>();
        String username=form.getUsername();
        if(username==null||username.trim().isEmpty()){
            errors.put("username","用户名不能为空！");
        }
        else if(username.length()<3||username.length()>10){
            errors.put("username","用户名长度必须在3~10之间！");
        }
        String user_password=form.getUser_password();
        if(user_password==null||user_password.trim().isEmpty()){
            errors.put("user_password","密码不能为空！");
        }
        else if(user_password.length()<6||user_password.length()>12){
            errors.put("user_password","密码长度必须在6~12之间！");
        }
        if(errors.size()>0){
            /**保存错误信息，保存表单信息，转发到login.jsp*/
            request.setAttribute("errors",errors);
            request.setAttribute("form",form);
            return "/FrontJsps/user/login.jsp";
        }
        /**调用service完成登录*/
        try{
            User user = userService.login(form);
            request.getSession().setAttribute("session_user",user);
            /**添加给用户购物车*/
            request.getSession().setAttribute("cart",new Cart());
            return "/FrontJsps/main.jsp";
        }catch (UserException e){
            request.setAttribute("message",e.getMessage());
            request.setAttribute("form",form);
            return "/FrontJsps/user/login.jsp";
        }
    }

    /**
     * 退出功能
     * */
    public String quit(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        request.getSession().invalidate();
        return "/FrontJsps/main.jsp";
    }

    /**s
     * 修改分类
     * */
    public String edit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        /**封装表单数据*/
        User user = CommonUtils.toBean(request.getParameterMap(),User.class);
        /**调用service完成修改工作*/
        userService.edit(user);
        request.getSession().invalidate();
        return "/FrontJsps/main.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        String route = new String("");
        if(method.equals("register"))
            route = regist(request,response);
        else if(method.equals("activate"))
            route = activate(request,response);
        else if(method.equals("login"))
            route = login(request,response);
        else if(method.equals("quit"))
            route = quit(request,response);
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
