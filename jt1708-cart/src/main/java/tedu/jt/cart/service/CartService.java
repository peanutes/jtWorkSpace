package tedu.jt.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.jt.cart.mapper.CartMapper;
import tedu.jt.cart.pojo.Cart;

import java.util.Date;
import java.util.List;

/**
 * 购物车业务逻辑
 *
 * @author Administrator
 * @create 2017-12-22 12:51
 */
@Service
public class CartService extends BaseService<Cart> {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    public List<Cart> getMyCart(Long userId){
       Cart cart = new Cart();
       cart.setUserId(userId);
       List<Cart> cartList = cartMapper.select(cart);
       return cartList;
    }

    /**
     * 保存商品到购物车
     */
    public Integer saveCart(Cart cart){
        Cart params = new Cart();
        params.setUserId(cart.getUserId());
        params.setItemId(cart.getItemId());
        Cart curcart = super.queryByWhere(params);
        if(curcart==null){
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cartMapper.insertSelective(cart);
            return 200;
        }else {
            cart.setNum(cart.getNum()+curcart.getNum());
            cart.setUpdated(new Date());
            cartMapper.updateMycart(cart);
            return 202;
        }
    }

    /**
     * 更新商品数量
     * @param userId
     * @param itemId
     * @param num
     */
    public void updateCart(Long userId,Long itemId,Integer num){
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cart.setNum(num);
        cartMapper.updateMycart(cart);
    }

}
