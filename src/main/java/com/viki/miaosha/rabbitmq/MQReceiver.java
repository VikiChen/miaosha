package com.viki.miaosha.rabbitmq;

import com.viki.miaosha.domain.MiaoshaOrder;
import com.viki.miaosha.domain.MiaoshaUser;
import com.viki.miaosha.redis.RedisService;
import com.viki.miaosha.service.GoodsService;
import com.viki.miaosha.service.MiaoshaService;
import com.viki.miaosha.service.OrderService;
import com.viki.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;



    private static Logger log=LoggerFactory.getLogger(MQReceiver.class);


    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message){
        log.info("receive message:"+message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user=mm.getUser();
        long goodsId=mm.getGoodsId();

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);//10个商品，req1 req2
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);

    }

//    @RabbitListener(queues = MQConfig.QUEUE)
//    public void receiveMessage(String message){
//         log.info("receive message:"+message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(String message){
//        log.info("receive topic1 message:"+message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2(String message){
//        log.info("receive topic2 message:"+message);
//    }
//
//
//    @RabbitListener(queues = MQConfig.HEADER_QUEUE)
//    public void receiveHeaderQueue(String message){
//        log.info("receive headerQueue message:"+new String(message));
//    }


}
