package dzc.bookStore.category.dao;

import cn.itcast.jdbc.TxQueryRunner;
import dzc.bookStore.category.domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查询所有分类
     * */
    public List<Category> findAll(){
        try{
            String sql = "select * from category";
            return qr.query(sql,new BeanListHandler<Category>(Category.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加分类
     * */
    public void add(Category category){
        try{
            String sql = "insert into category values(?,?)";
            qr.update(sql,category.getCategory_id(),category.getCategory_name());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除分类
     * */
    public void delete(String category_id){
        try{
            String sql = "delete from category where category_id = ?";
            qr.update(sql,category_id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载分类
     * */
    public Category load(String category_id){
        try{
            String sql = "select * from category where category_id = ?";
            return qr.query(sql,new BeanHandler<Category>(Category.class),category_id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 修改分类
     * */
    public void edit(Category category){
        try{
            String sql = "update category set category_name = ? where category_id = ?";
            qr.update(sql,category.getCategory_name(),category.getCategory_id());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
