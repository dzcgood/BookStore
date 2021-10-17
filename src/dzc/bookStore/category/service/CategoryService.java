package dzc.bookStore.category.service;

import dzc.bookStore.book.dao.BookDao;
import dzc.bookStore.category.dao.CategoryDao;
import dzc.bookStore.category.domain.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    private BookDao bookDao = new BookDao();
    /**
     * 查询所有分类
     * */
    public List<Category> findAll(){
        return categoryDao.findAll();
    }


    /**
     * 添加分类
     * */
    public void add(Category category){
        categoryDao.add(category);
    }

    /**
     * 删除分类
     * */
    public void delete(String category_id) throws CategoryException{
        /**通过category_id获取该分类下图书本书*/
        int count =bookDao.getCountByCategory_id(category_id);
        /**该分类有图书，不能删除*/
        if(count>0)throw new CategoryException("该分类下还有图书，不能删除！");
        else categoryDao.delete(category_id);
    }

    /**
     * 加载分类
     * */
    public Category load(String category_id){
        return categoryDao.load(category_id);
    }

    /**
     * 修改分类
     * */
    public void edit(Category category){
        categoryDao.edit(category);
    }
}
