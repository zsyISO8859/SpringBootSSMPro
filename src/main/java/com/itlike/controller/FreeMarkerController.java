package com.itlike.controller;

import com.itlike.pojo.TbHero;
import com.itlike.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/4 10:46
 */

@Controller
public class FreeMarkerController {
    //为什么要使用网页静态化技术
    //	网页静态化技术和缓存技术的共同点都是为了减轻数据库的访问压力
    //	而网页静态化比较适合大规模且相对变化不太频繁的数据。另外网页静态化还有利于SEO。
    //	将网页以纯静态化的形式展现，就可以使用Nginx这样的高性能的web服务器来部署
    //	Nginx可以承载5万的并发，而Tomcat只有几百

    @Autowired
    HeroService heroService;

    @RequestMapping("/user")
    @ResponseBody
    public void getUser(Integer id){
        TbHero hero = heroService.getDataById(id);

    }
}
