package tedu.jt.cart.mapper;

import tedu.jt.cart.pojo.Cart;
import tedu.jt.common.mapper.SysMapper;

/**
 * 购物车Mapper
 *
 * @author Administrator
 * @create 2017-12-22 12:32
 */
public interface CartMapper extends SysMapper<Cart> {

    public void updateMycart(Cart cart);

}
