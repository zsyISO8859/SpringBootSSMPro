package com.itlike.mapper;

import com.itlike.pojo.TbHero;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/7/13 17:43
 */

@Repository
public interface HeroMapper extends Mapper<TbHero> {
        TbHero getHeroWithId(Integer id);
}
