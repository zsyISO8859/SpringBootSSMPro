package com.itlike.controller;

import com.itlike.mapper.HeroMapper;
import com.itlike.pojo.TbHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/8/2 15:09
 */

@Controller
@RequestMapping("/type")
public class RedisTemplateTest {
    @Autowired
    private HeroMapper heroMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/getType")
    @ResponseBody
    public TbHero getTypeById(Integer id) {
        TbHero tbHero = (TbHero) redisTemplate.boundHashOps("typeService").get(id);
        //查询：每次都从redis中取数据，redis中不存在就从数据库查出一份并放入缓存中，redis中存在就直接从redis中取出来。
        if (tbHero == null) {
            TbHero tbHeroData = heroMapper.selectByPrimaryKey(id);
            redisTemplate.boundHashOps("typeService").put(tbHeroData.getId(), tbHeroData);
            System.out.println("数据库查询");
            return tbHeroData;
        }
        System.out.println("redis查询");
        return tbHero;
    }

    @RequestMapping("/insertTypeById")
    @ResponseBody
    public void insertTypeById() {
        //新增：每次新增完单据就删除redis对应的key下次查询的时候就会自动从数据库读取。
        TbHero tbHero = new TbHero();
        tbHero.setUsername("zypDog");
        tbHero.setProfession("打狗");
        tbHero.setPhone("1109146376");
        tbHero.setEmail("zyp@qq.com");
        heroMapper.insertSelective(tbHero);
        redisTemplate.boundHashOps("typeService").delete(tbHero.getId());
    }

    @RequestMapping("/deleteTypeById")
    @ResponseBody
    public void deleteTypeById(Integer id) {
        //删除：每次先删除key再删除数据 下次查询的时候就会自动从数据库读取。
        redisTemplate.boundHashOps("typeService").delete(id);
        heroMapper.deleteByPrimaryKey(id);
    }

    @RequestMapping("/updateTypeById")
    @ResponseBody
    public void updateTypeById(Integer id) {
        //更新问题：
        //更新可能造成一种情况业务上某种数据多了一条 某种数据少了一条
        //例如 分类A中含有子分类a 分类B中含有子分类b 这时候将子分类a更新成B的子分类  这时候A少了一条数据，B多了一条数据
        //解决方案： 1.根据更新前的数据查出原来的对象并从redis删除对应key 2.根据需要更新的数据从redis中查出并删除

        //例子：
        //1. 根据广告id, 到数据库中查询原来的广告对象
        //Content oldContent = contentDao.selectByPrimaryKey(content.getId());
        //2. 根据原来的广告对象中的分类id, 到redis中删除对应的广告集合数据
        //redisTemplate.boundHashOps(Constants.CONTENT_LIST_REDIS).delete(oldContent.getCategoryId());
        //3. 根据传入的最新的广告对象中的分类id, 删除redis中对应的广告集合数据
        //redisTemplate.boundHashOps(Constants.CONTENT_LIST_REDIS).delete(content.getCategoryId());
        //4. 将新的广告对象更新到数据库中
        //contentDao.updateByPrimaryKeySelective(content);

    }

}
