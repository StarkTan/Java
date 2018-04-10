package com.stark.design23.memento;

/**
 * Created by Stark on 2018/3/26.
 * 测试
 */
public class App {

    public static void main(String[] args) {
        StatusCache statusCache = new StatusCache();
        Task task = new Task();
        task.setStatus("status #1");
        statusCache.addStatus(task.savaStatus());
        task.setStatus("status #2");
        statusCache.addStatus(task.savaStatus());
        task.setStatus("status #3");
        statusCache.addStatus(task.savaStatus());
        task.setStatus("status #4");
        statusCache.addStatus(task.savaStatus());

        System.out.println("current status: " + task.getStatus());

        task.setStutasfromCache(statusCache.getStatus(0));
        System.out.println("first status cache: " + task.getStatus());
        task.setStutasfromCache(statusCache.getStatus(1));
        System.out.println("second status cache: " + task.getStatus());
        task.setStutasfromCache(statusCache.getStatus(2));
        System.out.println("third status cache: " + task.getStatus());
        task.setStutasfromCache(statusCache.getStatus(3));
        System.out.println("fourth status cache: " + task.getStatus());

    }
}
