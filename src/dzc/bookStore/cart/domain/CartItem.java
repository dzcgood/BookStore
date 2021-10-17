package dzc.bookStore.cart.domain;

import dzc.bookStore.book.domain.Book;

import java.math.BigDecimal;

/**
 * 购物车条目类
 * */
public class CartItem {
    private Book book; //商品
    private int count; //数量

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 价格小计
     * 使用BigDecimal处理二进制运算误差问题
     * */
    public double getSubtotal(){
        BigDecimal data1=new BigDecimal(book.getBook_price()+"");
        BigDecimal data2=new BigDecimal(count+"");
        return data1.multiply(data2).doubleValue();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }
}

