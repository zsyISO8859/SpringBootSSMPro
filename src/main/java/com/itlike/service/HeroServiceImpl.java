package com.itlike.service;

import com.itlike.mapper.HeroMapper;
import com.itlike.pojo.TbHero;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/7/14 10:03
 */

@Service
public class HeroServiceImpl implements HeroService, ServletContextAware {
    @Autowired
    private HeroMapper heroMapper;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private ServletContext servletContext;

    @Override
    public List<TbHero> getAllHero() {
        return heroMapper.selectAll();
    }

    @Override
    public TbHero getHeroById(Integer id) {
        return heroMapper.getHeroWithId(id);
    }

    @Override
    public List<TbHero> getFreeMarkerData() {

        return null;
    }

    @Override
    public TbHero getDataById(Integer id) {
        TbHero tbHero = heroMapper.selectByPrimaryKey(id);
        try {
            Configuration configuration = freeMarkerConfigurer.getConfiguration();

            Template template = configuration.getTemplate("freemarkerindex.ftl");
            String realPath = this.servletContext.getRealPath(id + ".html");
            System.out.println(realPath);
            OutputStreamWriter outPut = new OutputStreamWriter(new FileOutputStream(realPath));

            ArrayList<String> list = new ArrayList<>();
            list.add("a");
            list.add("s");
            list.add("d");

            HashMap<String, Object> map = new HashMap<>();
            map.put("hero",tbHero);
            map.put("list",list);
            map.put("time",new Date());
            map.put("price",18959223);
            template.process(map,outPut);
            outPut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbHero;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
