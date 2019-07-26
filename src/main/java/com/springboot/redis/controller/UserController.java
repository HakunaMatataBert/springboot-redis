package com.springboot.redis.controller;

import com.google.gson.Gson;
import com.springboot.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/view")
public class UserController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @RequestMapping("/toLogin")
    public String Login(String name,String age,String sex){
        int count = 0;
        while (true){
            Random random = new Random();
            int ramnum =  random.nextInt();
            stringRedisTemplate.opsForValue().set("name"+ramnum,name+ramnum);


            User user = new User();
            user.setName(name+ramnum);
            user.setAge(age);
            user.setSex(sex);

            Gson gson = new Gson();
            redisTemplate.opsForValue().set("user",gson.toJson(user));

            System.err.println("获取name的值："+stringRedisTemplate.opsForValue().get("name"+ramnum));
            System.err.println("获取user的值："+redisTemplate.opsForValue().get("user"));
            count++;
            System.err.println(count);
            if (count>100000){
                break;
            }
        }
        return "count";

    }

    @RequestMapping("/ttest")
    public String Test(String username){

        return "登陆数据";
    }

}
