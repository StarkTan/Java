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
        Configuration configuration = new Configuration().configure();
        SessionFactory factory =
                configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class,1);
        System.out.println(user);
        transaction.commit();
        session.close();
        factory.close();
    }
}
