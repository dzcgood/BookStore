package dzc.bookStore.user.service;

import dzc.bookStore.category.domain.Category;
import dzc.bookStore.user.dao.UserDao;
import dzc.bookStore.user.domain.User;
import dzc.bookStore.user.service.UserException;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 注册功能
     * 先校验用户名和邮箱是否被注册
     * */
    public void regist(User form) throws UserException {

        /**校验用户名*/
        User user = userDao.findByUserName(form.getUsername());
        if(user!=null)  throw new UserException("该用户名已被注册！");

        /**校验邮箱*/
        user = userDao.findByUserEmail(form.getUser_email());
        if(user!=null)  throw new UserException("该邮箱已被注册！");

        /**插入用户到数据库*/
        userDao.add(form);
    }

    /**
     * 激活功能
     * */
    public void active(String code)throws UserException{
        /**使用code查询数据库得到user*/
        User user = userDao.findByUserCode(code);
        /**如果user不存在，说明激活码无效*/
        if(user==null)throw new UserException("激活码无效！");
        /**如果用户状态是已激活，则报错*/
        if(user.isUser_state())throw new UserException("您已经激活过了，别再激活了！");
        /**修改用户的状态*/
        userDao.updateState(user.getUserid(),true);
    }

    /**
     * 登录功能
     * */
    public User login(User form)throws UserException{
        /**使用username查询，得到user*/
        User user = userDao.findByUserName(form.getUsername());
        /**若user为null，抛出异常*/
        if(user==null)throw new UserException("用户名不存在！");
        /**比较密码是否正确，若错误，抛出异常*/
        if(!user.getUser_password().equals(form.getUser_password()))throw new UserException("密码错误！");
        /**判断用户是否激活，若未激活，抛出异常*/
        if(!user.isUser_state())throw new UserException("尚未激活！");
        return user;
    }

    /**
     * 查询所有用户
     * */
    public List<User> findAll(){
        return userDao.findAll();
    }

    /**
     * 加载用户信息
     * */
    public User load(String userid){
        return userDao.load(userid);
    }

    /**
     * 修改用户信息
     * */
    public void edit(User user){
        userDao.edit(user);
    }
}
