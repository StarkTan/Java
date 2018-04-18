package com.stark;

import com.stark.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

/**
 * Created by Stark on 2018/4/13.
 */
public class App {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration().configure();
        final SessionFactory factory =
                configuration.buildSessionFactory();
        Session session = factory.openSession();
        User user = session.get(User.class, 1);
        User user2 = session.get(User.class, 1);
        System.out.println(user);
        System.out.println(user2);
        Session session2 = factory.openSession();
        User user3 = session2.get(User.class, 1);
        System.out.println(user3);
        session.close();
        session2.close();
        factory.close();
    }
}
