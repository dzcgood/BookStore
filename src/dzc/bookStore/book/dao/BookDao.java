package dzc.bookStore.book.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import dzc.bookStore.book.domain.Book;
import dzc.bookStore.category.domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查找所有图书
     * */
    public List<Book> findAll(){
        try{
            String sql="select * from book where del = 0";
            return qr.query(sql,new BeanListHandler<Book>(Book.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 按分类查找图书
     * */
    public List<Book> findByCategory(String category_id){
        try{
            String sql="select * from book where category_id = ? and del = 0";
            return qr.query(sql,new BeanListHandler<Book>(Book.class),category_id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 按id查找图书
     * */
    public Book findByBook_id(String book_id){
        try{
            /**需要在book对象中保存category的信息*/
            String sql="select * from book where book_id = ?";
            /**使用Map，映射出两个对象，再将这两个对象建立联系*/
            Map<String,Object> map = qr.query(sql,new MapHandler(),book_id);
            Category category = CommonUtils.toBean(map,Category.class);
            Book book = CommonUtils.toBean(map,Book.class);
            book.setCategory(category);
            return book;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 按category_id查找该分类图书本数
     * */
    public int getCountByCategory_id(String category_id){
        Number cnt;
        try{
            String sql="select count(*) from book where category_id = ? and del = 0";
            cnt = (Number)qr.query(sql,new ScalarHandler(),category_id);
            return cnt.intValue();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除图书 假删除
     * */
    public void delete(String book_id){
        try{
            String sql="update book set del = 1 where book_id = ?";
            qr.update(sql,book_id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑图书信息
     * */
    public void edit(Book book){
        try{
            String sql="update book set book_name = ?, book_price = ?," +
                    "book_author = ?,book_image = ?, category_id = ? where book_id = ?";
            Object[]params = {book.getBook_name(),book.getBook_price(),book.getBook_author(),
                    book.getBook_image(),book.getCategory().getCategory_id(),book.getBook_id()};
            qr.update(sql,params);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
