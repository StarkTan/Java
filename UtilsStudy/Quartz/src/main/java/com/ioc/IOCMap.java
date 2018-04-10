package com.ioc;

import com.Utils;
import com.ioc.anno.Bean;
import com.ioc.anno.Require;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Stark on 2018/1/9.
 * 使用一个HashMap作为一个IOC容器
 */
public class IOCMap {
    private HashMap<String, Object> map;

    public IOCMap() {
        this(new String[0]);
    }

    public IOCMap(String[] packages) {
        map = new HashMap<>();
        //开始扫描包
        if (packages.length == 0) {
            String path = this.getClass().getPackage().getName();
            packages = new String[]{path};
        }
        //获取到包下面的类
        Set<Class> set = new HashSet<>();
        for (String path : packages) {
            set.addAll(Utils.getClasses(path));
        }
        setBeans(map, set, true);
    }

    private void setBeans(HashMap<String, Object> map, Set<Class> clazzs, boolean first) {
        if (clazzs.isEmpty()) return;
        Set<Class> nextSet = new HashSet<>();
        for (Class clazz : clazzs) {
            Bean beanAnno = (Bean) clazz.getDeclaredAnnotation(Bean.class);
            if (beanAnno != null) {
                String name = beanAnno.name();
                if (map.containsKey(name) && map.get(name) != null) {
                    throw new RuntimeException("实例名称配置重复:" + name);
                }
                Field[] fields = clazz.getDeclaredFields();
                boolean enough = true;
                //判断当前类的属性依赖
                for (Field field : fields) {
                    Require declaredAnno = field.getDeclaredAnnotation(Require.class);
                    if (declaredAnno != null) {
                        String name1 = declaredAnno.name();
                        if (!first && !map.containsKey(name1)) {
                            throw new RuntimeException("没有这个实例:" + name1);
                        }
                        if (map.get(name1) == null) {
                            nextSet.add(clazz);
                            enough = false;
                            break;
                        }
                    }
                }
                if (enough) {
                    Object object;
                    try {
                        Constructor constructor = clazz.getDeclaredConstructor();
                        object = constructor.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(clazz.getName() + ": 无参构造函数出错");
                    }
                    for (Field field : fields) {
                        Require declaredAnno = field.getDeclaredAnnotation(Require.class);
                        String name1 = declaredAnno.name();
                        field.setAccessible(true);
                        try {
                            field.set(object, map.get(name1));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    map.put(name, object);
                } else if (!map.containsKey(name)) {
                    map.put(name, null);
                }
            }
        }
        //当一次处理没有减少Class数，则判断引用重复
        if (nextSet.size() == clazzs.size()) {
            throw new RuntimeException("引用关系出现循环");
        }
        setBeans(map, nextSet, false);
    }

    public Object getBean(String name) {
        return map.get(name);
    }
}
