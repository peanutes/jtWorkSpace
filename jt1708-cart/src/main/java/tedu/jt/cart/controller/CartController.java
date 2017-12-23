package tedu.jt.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.cart.pojo.Cart;
import tedu.jt.cart.service.CartService;
import tedu.jt.common.vo.SysResult;

import java.util.List;

/**
 * 购物车控制跳转
 *
 * @author Administrator
 * @create 2017-12-22 12:54
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 查询我的购物车的商品
     * @param userId
     * @return
     */
    @RequestMapping("/query/{userId}")
    @ResponseBody
    public SysResult getCart(@PathVariable Long userId){

        List<Cart> cartList= cartService.getMyCart(userId);

        return SysResult.oK(cartList);
    }

    /**
     * 新增商品
     * @param cart
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult savaCart(Cart cart){
        Integer codeStatus = cartService.saveCart(cart);
        if(200==codeStatus){
            return SysResult.oK();
        }else if(202==codeStatus) {
            return SysResult.build(202,"商品存在已累加");
        }
        return SysResult.build(201,"新增商品失败");
    }

    /**
     * 修改商品数量
     * @param userId
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping("/update/num/{userId}/{itemId}/{num}")
    public SysResult updateNum(@PathVariable Long userId,@PathVariable Long itemId,@PathVariable Integer num){
        cartService.updateCart(userId,itemId,num);
        return SysResult.oK();
    }
    @RequestMapping("/delete/{userId}/{itemId}")
    public SysResult deletecart(@PathVariable Long userId,@PathVariable Long itemId){
        Cart cart = new Cart();
        cart.setItemId(itemId);
        cart.setUserId(userId);
        cartService.deleteByWhere(cart);
        return SysResult.oK();
    }

}
