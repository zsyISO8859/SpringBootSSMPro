package com.itlike.service;

import com.itlike.pojo.TbHero;

import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/7/14 10:02
 */


public interface HeroService {
    List<TbHero> getAllHero();
    TbHero getHeroById(Integer id);

    List<TbHero> getFreeMarkerData();

    TbHero getDataById(Integer id);
}
