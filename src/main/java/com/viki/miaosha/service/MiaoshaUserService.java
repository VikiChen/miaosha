package com.viki.miaosha.service;

import com.viki.miaosha.domain.MiaoshaUser;
import com.viki.miaosha.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface MiaoshaUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    public MiaoshaUser getById(long id) ;

    public MiaoshaUser getByToken(HttpServletResponse response, String token);
    public  boolean updatePassword(String token,long id,String formPass);
    public boolean login(HttpServletResponse response, LoginVo loginVo);

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user);
}
