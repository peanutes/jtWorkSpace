package tedu.jt.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.jt.common.service.HttpClientService;
import tedu.jt.web.pojo.Cart;


import java.util.List;
import java.util.Map;

/**
 * 购物车控制跳转
 *
 * @author Administrator
 * @create 2017-12-22 15:44
 */
@Service
public class CartService {
    @Autowired
    private HttpClientService httpClientService;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 查看购物车
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Cart> show(Long userId) throws Exception {
        String url = "http://cart.jt.com/cart/query/" + userId;
        String jsondata = httpClientService.doGet(url);
        if (StringUtils.isNotEmpty(jsondata)) {
            JsonNode jsonNode = MAPPER.readTree(jsondata);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructType(List.class));
            }
            return (List<Cart>) obj;
        }
        return null;
    }

    /**
     * 添加商品到购物车
     * @param cart
     * @throws Exception
     */
    public void addCart(Cart cart) throws Exception {
        String url = "http://cart.jt.com/cart/save";
        Map<String,String> params = new HashedMap();
        params.put("userId", cart.getUserId()+"");
        params.put("itemId", cart.getItemId()+"");
        params.put("itemTitle", cart.getItemTitle());
        params.put("itemPrice", cart.getItemPrice()+"");
        params.put("itemImage", cart.getItemImage());
        params.put("num", cart.getNum()+"");
        httpClientService.doPost(url,params,"utf-8");
    }

    /**
     * 修改购物车中商品的数量
     * @param cart
     * @throws Exception
     */
    public void updateNum(Cart cart) throws Exception {
        String url = "http://cart.jt.com/cart/update/num"+cart.getUserId()+"/"+cart.getItemId()+"/"+cart.getNum();
        httpClientService.doGet(url);
    }
    public void deletecart(Long userId,Long itemId) throws Exception {
        String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
        httpClientService.doGet(url);
    }

}
