package dzc.bookStore.user.dao;

import cn.itcast.jdbc.TxQueryRunner;
import dzc.bookStore.category.domain.Category;
import dzc.bookStore.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 按用户名查找
     * */

    public User findByUserName(String user_name){
        try{
            String sql="Select * from tb_user where username=?";
            return qr.query(sql,new BeanHandler<User>(User.class),user_name);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 按邮箱查找
     * */

    public User findByUserEmail(String user_email){
        try{
            String sql="select * from tb_user where user_email =?";
            return qr.query(sql,new BeanHandler<User>(User.class),user_email);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入user到数据库
     * */
    public void add(User user){
        try{
            String sql="insert into tb_user values(?,?,?,?,?,?)";
            Object[] params={user.getUserid(),user.getUsername(),user.getUser_password(),
                    user.getUser_email(),user.getUser_code(), user.isUser_state()};
            qr.update(sql,params);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 按激活码查找
     * */
    public User findByUserCode(String user_code){
        try{
            String sql="select * from tb_user where user_code =?";
            return qr.query(sql,new BeanHandler<User>(User.class),user_code);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改指定用户的指定状态
     */
    public void updateState(String userid, boolean state){
        try{
            String sql="update tb_user set user_state = ? where userid = ?";
            qr.update(sql,state,userid);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有分类
     * */
    public List<User> findAll(){
        try{
            String sql = "select * from tb_user";
            return qr.query(sql,new BeanListHandler<User>(User.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载用户
     * */
    public User load(String userid){
        try{
            String sql = "select * from tb_user where userid = ?";
            return qr.query(sql,new BeanHandler<User>(User.class),userid);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改用户信息
     * */
    public void edit(User user){
        try{
            String sql = "update tb_user set username = ?, user_password = ?, user_email = ?, user_state = ?" +
                    " where userid = ?";
            qr.update(sql,user.getUsername(),user.getUser_password(),user.getUser_email(),user.isUser_state(),user.getUserid());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

