package com.stark.dao;

import org.hibernate.Session;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Stark on 2018/4/19.
 */
public class AbstractDao implements Dao<Entity> {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Entity save(Entity entity) {
        return null;
    }

    public Entity merge(Entity entity) {
        return null;
    }

    public boolean delete(Entity entity) {
        return false;
    }

    public boolean deleteById(String id) {
        return false;
    }

    public Entity update(Entity entity) {
        return null;
    }

    public Entity saveOrUpdate(Entity entity) {
        return null;
    }

    public Entity getById(String id) {
        return null;
    }

    public List<Entity> getAll() {
        return null;
    }
}
