package tedu.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tedu.jt.web.pojo.Cart;

import tedu.jt.web.service.CartService;
import tedu.jt.web.threadlocal.UserThreadlocal;


import java.util.List;

/**
 * 前台购物车控制跳转
 *
 * @author Administrator
 * @create 2017-12-22 15:41
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 显示购物车信息
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/show")
    public String show(Model model) throws Exception {
        Long userId = UserThreadlocal.getUserId();
        List<Cart> cartList = cartService.show(userId);
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 加入购物车
     * @param cart
     * @return
     * @throws Exception
     */
    @RequestMapping("/add/{itemId}.html")
    public String addCart(Cart cart,@PathVariable Long itemId) throws Exception {
        Long userId = UserThreadlocal.getUserId();
        cart.setUserId(userId);
        cartService.addCart(cart);
        return "redirect:/cart/show.html";
    }
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public String updateNum(Cart cart) throws Exception {
        Long userId = UserThreadlocal.getUserId();
        cart.setUserId(userId);
        cartService.updateNum(cart);
        return "";

    }
    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId) throws Exception {
        Long userId = UserThreadlocal.getUserId();
        cartService.deletecart(userId,itemId);
        return "redirect:/cart/show.html";
    }
}
