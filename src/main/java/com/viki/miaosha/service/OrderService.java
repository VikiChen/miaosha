package com.viki.miaosha.service;

import com.viki.miaosha.domain.MiaoshaOrder;
import com.viki.miaosha.domain.MiaoshaUser;
import com.viki.miaosha.domain.OrderInfo;
import com.viki.miaosha.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) ;

    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) ;


   public OrderInfo getOrderById(long orderId);

   public void deleteOrders();
}
