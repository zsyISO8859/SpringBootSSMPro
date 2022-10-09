package com.itlike.controller;

import com.itlike.mapper.HeroMapper;
import com.itlike.pojo.TbHero;
import com.itlike.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/7/13 17:33
 */


@Controller
public class TestController {

    //在controller里面直接注入mapper会报红色 但是不影响使用
    //解决方法：在mapper接口标注@Repository  或者在controller中用@Resource引入mapper
    @Autowired
    HeroMapper heroMapper;

    @Autowired
    HeroService heroService;

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }

    //直接使用通用mapper
    @RequestMapping("test")
    @ResponseBody
    public String test() {
        List<TbHero> tbHeroes = heroMapper.selectAll();
        for (TbHero tbHero : tbHeroes) {
            System.out.println(tbHero);
        }
        return "hello";
    }

    @RequestMapping("serviceFun")
    @ResponseBody
    public String serviceFun() {
        TbHero heroById = heroService.getHeroById(1);
        System.out.println(heroById);
        return "hello";
    }

    //测试新增记录且返回id 且email字段不存入数据库
    @RequestMapping("testCommonMapper")
    @ResponseBody
    public String testCommonMapper() {
        TbHero tbHero = new TbHero();
        tbHero.setUsername("name");
        tbHero.setEmail("email");
        tbHero.setProfession("qqqq");
        tbHero.setPhone("110");
        heroMapper.insert(tbHero);
        System.out.println(tbHero);
        return "hello";
    }

    //测试跳转页面 页面转发不能加上@ResponseBody标签
    @RequestMapping("testdispacher")
    public String testdispacher(Model model) {
        model.addAttribute("name", "itlike000001");
        model.addAttribute("nametest", "<H1>hahahah</H1>");

        TbHero tbHero = new TbHero();
        tbHero.setId(1);
        tbHero.setUsername("name");
        tbHero.setPhone("110");
        model.addAttribute("hero", tbHero);
        return "hello";
    }

    //测试thymeleaf通过url传参数
    //TODO 目前还没测试通过 2022/07/14
    @RequestMapping("getparams")
    public String getparams(Model model) {
        model.addAttribute("name", "itlike");
        TbHero tbHero = new TbHero();
        tbHero.setId(1);
        tbHero.setUsername("name");
        tbHero.setPhone("110");
        model.addAttribute("hero", tbHero);
        return "hello";
    }

    //测试thymeleaf的局部变量
    @RequestMapping("variable")
    public String variable(Model model) {
        List<TbHero> allHero = heroService.getAllHero();
        model.addAttribute("heros", allHero);
        model.addAttribute("name", "namevalue");
        return "shit";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public TbHero getData() {
        TbHero heroById = heroService.getHeroById(1);
        return heroById;
    }

    @RequestMapping("/getDataList")
    @ResponseBody
    public List<TbHero> getListData() {
        return heroService.getAllHero();
    }
}
