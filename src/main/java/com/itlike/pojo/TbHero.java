package com.itlike.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/7/13 16:54
 */

@Data
@Table(name = "tb_hero")
public class TbHero implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private String profession;
    private String phone;

    //该字段数据不存储到数据库
    @Transient
    private String email;
}
