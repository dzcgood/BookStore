package dzc.bookStore.cart.domain;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

public class Cart {
    public Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();//键为book_id

    /**
     * 计算总价
     * 使用BigDecimal处理二进制运算误差
     * */
    public double getTotal(){
        BigDecimal total =new BigDecimal("0");
        for(CartItem cartItem : map.values()){
            BigDecimal subtotal = new BigDecimal(""+cartItem.getSubtotal());
            total=total.add(subtotal);
        }
        return total.doubleValue();
    }

    /**添加条目*/
    public void add(CartItem cartItem){
        /**原先已经存在*/
        if(map.containsKey(cartItem.getBook().getBook_id())){
            /**返回原条目*/
            CartItem cartItem1 = map.get(cartItem.getBook().getBook_id());
            /**设置新数量*/
            cartItem1.setCount(cartItem1.getCount()+cartItem.getCount());

            map.put(cartItem.getBook().getBook_id(),cartItem1);
        }else{
            /**原先不存在*/
            map.put(cartItem.getBook().getBook_id(),cartItem);
        }
    }

    /**清空购物车*/
    public void clear(){
        map.clear();
    }

    /**删除条目*/
    public void delete(String book_id){
        map.remove(book_id);
    }

    /**获取购物车内容*/
    public Collection<CartItem> getCollection(){
        return map.values();
    }

}
