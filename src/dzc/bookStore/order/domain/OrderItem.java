package dzc.bookStore.order.domain;

import dzc.bookStore.book.domain.Book;

public class OrderItem {
    private String item_id;
    private int item_count;/**数量*/
    private double item_subtotal;/**小计*/
    private Order order;/**所属订单*/
    private Book book;/**要购买的图书*/

    @Override
    public String toString() {
        return "OrderItem{" +
                "item_id='" + item_id + '\'' +
                ", item_count=" + item_count +
                ", item_subtotal=" + item_subtotal +
                ", order_id=" + order +
                ", book_id=" + book +
                '}';
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public double getItem_subtotal() {
        return item_subtotal;
    }

    public void setItem_subtotal(double item_subtotal) {
        this.item_subtotal = item_subtotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
