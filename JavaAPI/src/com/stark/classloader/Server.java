package com.stark.classloader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Stark on 2018/4/11.
 * 热加载的Server
 */
public class Server extends Thread {
    private final String parentPath;
    private final List<String> classList = new ArrayList<>();
    private final Map<String, Long> classMap = new HashMap<>();

    public Server(String parentPath) {
        this.parentPath = parentPath;
        File file = new File(parentPath);
        if (!file.exists()) {
            throw new RuntimeException(parentPath + " is not exists");
        }
        if (!file.isDirectory()) {
            throw new RuntimeException(parentPath + "is not a directory");
        }
    }

    //遍历文件，获取所有的class文件列表
    public void getList(String path) {
        classList.clear();
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        String[] list = file.list();
        if (list == null) return;
        for (String fileName : list) {
            if (fileName.endsWith(".class")) {
                classList.add(absolutePath + "/" + fileName);
            }
            if (!fileName.contains(".")) {
                getList(absolutePath + "/" + fileName);
            }
        }
    }

    //判断是否需要重新加载类
    public boolean ifChanged() {
        boolean res = false;
        if (classList.size() != classMap.size()) {
            classMap.clear();
            res = true;
        }
        for (String path : classList) {
            if (!classMap.containsKey(path)) {
                res = true;
                classMap.put(path, new File(path).lastModified());
            } else {
                Long last = classMap.get(path);
                Long current = new File(path).lastModified();
                if (!Objects.equals(last, current)) {
                    res = true;
                    classMap.put(path, current);
                }
            }
        }
        return res;
    }

    @Override
    public void run() {
        MyClassLoader myClassLoader;
        while (true) {
            try {

                getList(parentPath);
                boolean b = ifChanged();
                if (b) {
                    Class clazz = null;
                    //如果上一个classLoader中有线程在执行，线程没执行完classLoader不会被GC
                    myClassLoader = new MyClassLoader();
                    for (String classPath : classList) {
                        if (classPath.contains("Service")) {
                            clazz = myClassLoader.findClass(classPath);
                        } else {
                            myClassLoader.findClass(classPath);
                        }
                    }
                    Object o = clazz.newInstance();
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        System.out.println(method.invoke(o));
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
