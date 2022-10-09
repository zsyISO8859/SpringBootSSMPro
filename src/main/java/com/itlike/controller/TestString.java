package com.itlike.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/2 11:33
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-redis.xml"})
public class TestString {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testHashPut() {
        //测试hash类型放入redis
        redisTemplate.boundHashOps("userHash").put("name", "zypDog");
    }

    @Test
    public void testHashGet() {
        //测试从redis中取hash类型
        BoundHashOperations user = redisTemplate.boundHashOps("userHash");
        String str = (String) user.get("name");
        Long name = user.delete("name");
        Boolean delRes = user.hasKey("name");
        System.out.println(str + " " + name + " " + delRes);
    }

    @Test
    public void testListPush(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("qqq");
        arrayList.add("www");
        arrayList.add("ddd");

        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("qqq1");
        arrayList1.add("www1");
        arrayList1.add("ddd1");
        redisTemplate.boundListOps("myList").leftPush(arrayList);
        redisTemplate.boundListOps("myList").leftPush(arrayList1);
        redisTemplate.boundListOps("myList").leftPush("003");
    }

    @Test
    public void  testListGet(){
        List myList = redisTemplate.boundListOps("myList").range(0, -1);
        for (Object o : myList) {
            System.out.println(o);
        }
        redisTemplate.delete("myList");
    }

}
