package com.stark.dao;

import com.mysql.cj.api.Session;

import java.util.List;

/**
 * Created by Stark on 2018/4/18.
 * 数据库操作类
 */
public interface Dao<Entity> {

    Entity save(Entity entity);

    Entity merge(Entity entity);

    boolean delete(Entity entity);

    boolean deleteById(String id);

    Entity update(Entity entity);

    Entity saveOrUpdate(Entity entity);

    Entity getById(String id);

    List<Entity> getAll();
    
}
