package dzc.bookStore.admin.dao;


import cn.itcast.jdbc.TxQueryRunner;
import dzc.bookStore.admin.domain.Admin;
import dzc.bookStore.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 按管理账户名查找
     * */

    public Admin findByAdminName(String admin_name){
        try{
            String sql="select * from admin where admin_name=?";
            return qr.query(sql,new BeanHandler<Admin>(Admin.class),admin_name);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
