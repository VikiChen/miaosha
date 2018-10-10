package com.viki.miaosha.service;

import com.viki.miaosha.domain.MiaoshaUser;
import com.viki.miaosha.domain.OrderInfo;
import com.viki.miaosha.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.List;

public interface MiaoshaService {
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);

    public long getMiaoshaResult(Long id, long goodsId);

    public void reset(List<GoodsVo> goodsList);

    public String createMiaoshaPath(MiaoshaUser user, long goodsId);

    public boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode);

    public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId);

    public boolean checkPath(MiaoshaUser user, long goodsId, String path);
}
