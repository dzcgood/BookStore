package dzc.bookStore.book.service;

import dzc.bookStore.book.dao.BookDao;
import dzc.bookStore.book.domain.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();
    /**
     * 查找所有图书
     * */
    public List<Book> findAll(){
        return bookDao.findAll();
    }

    /**
     * 按分类查询图书
     * */
    public List<Book> findByCategory(String category_id){
        return bookDao.findByCategory(category_id);
    }

    /**
     * 加载图书
     * */
    public Book load(String book_id){
        return bookDao.findByBook_id(book_id);
    }

    /**
     * 删除图书
     * */
    public void delete(String book_id){
        bookDao.delete(book_id);
    }

    /**
     * 编辑图书信息
     * */
    public void edit(Book book){
        bookDao.edit(book);
    }
}
