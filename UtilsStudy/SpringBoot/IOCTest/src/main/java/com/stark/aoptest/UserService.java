package com.stark.aoptest;

import org.springframework.stereotype.Service;

/**
 * Created by Stark on 2018/1/16.
 * 服务类，需要被切面的对象
 */
@Service
public class UserService implements IUserService {

    @Override
    public void add(String id) {
        System.out.println("add id: " + id);
    }

    @Override
    public void del(String id) {
        System.out.println("del id: " + id);
    }

    public void update(String id) {
        System.out.println("update id:" + id);
    }
}
