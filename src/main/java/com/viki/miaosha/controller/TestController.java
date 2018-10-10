package com.viki.miaosha.controller;

import com.viki.miaosha.domain.User;
import com.viki.miaosha.rabbitmq.MQSender;
import com.viki.miaosha.redis.RedisService;
import com.viki.miaosha.redis.UserKey;
import com.viki.miaosha.result.Result;
import com.viki.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class TestController {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    MQSender mqSender;

    @RequestMapping("/")
    @ResponseBody
    public Result<String> home (){
       return Result.success("sds");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello (){
        return "hello word";
    }

    @RequestMapping("/thtml")
    public String hello2 (Model model){
        model.addAttribute("name","ksjdij li");
        return "hello";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<User> hello2 (){
        return Result.success(userService.getByid(1));
    }

    @RequestMapping("/tx")
    @ResponseBody
    public Result<Boolean> tx (){
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet (){
        User v1= redisService.get(UserKey.getById,""+1,User.class);
        return Result.success(v1);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet (){
        User user =new User();
        user.setId(1);
        user.setName("wdw");
        redisService.set(UserKey.getById,""+1,user);
        return Result.success(true);
    }

//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq (){
//        mqSender.send("hello");
//        return Result.success("success");
//    }
//
//    @RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> topic (){
//        mqSender.sendTopic("hello");
//        return Result.success("success");
//    }
//
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result<String> fanout (){
//        mqSender.sendFanout("hello");
//        return Result.success("success");
//    }
//
//
//    @RequestMapping("/mq/header")
//    @ResponseBody
//    public Result<String> header (){
//        mqSender.sendHeader("hello");
//        return Result.success("success");
//    }
}
