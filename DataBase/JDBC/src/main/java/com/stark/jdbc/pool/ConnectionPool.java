package com.stark.jdbc.pool;

import com.stark.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Strak on 2017/4/25.
 * 数据库链接链接池
 */
public class ConnectionPool
{
    private final static List<ConnectionWarp> pool = new ArrayList<ConnectionWarp>(10);
    private static int current = 0;

    private static void checkStatus()
    {
        Runnable run = () ->
        {
            System.out.println("开始回收");
            for (int i = 0; i < 5; i++) {
                ConnectionWarp connectionWarp = ConnectionPool.pool.get(i);
                if (!connectionWarp.flag) {
                    if(ConnectionPool.current>0)
                    {
                        System.out.println("回收:"+current);
                        ConnectionPool.current--;
                    }

                }
            }
            for (int i = 5; i < 10; i++) {
                ConnectionWarp connectionWarp = ConnectionPool.pool.get(i);
                if(connectionWarp==null)
                {
                    continue;
                }
                if (!connectionWarp.flag) {
                    System.out.println("回收:"+current);
                    ConnectionPool.current--;
                    try {
                        connectionWarp.con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ConnectionPool.pool.add(i, null);
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(run).start();
    }

    public ConnectionPool()
    {
        for (int i = 0; i < 5; i++)
            pool.add(new ConnectionWarp(JDBCUtils.getConn()));
        this.checkStatus();
    }

    public synchronized ConnectionWarp getConnection()
    {
        if (current < 5) {
            System.out.println("第" + current + "个链接被占用");
            return this.pool.get(current++).changeStatus();
        } else if (4 < current || current < 10) {
            System.out.println("第" + current + "个链接被占用");
            current++;
            return new ConnectionWarp(JDBCUtils.getConn());
        } else {
            return null;
        }
    }

    /**
     * connection封装
     */
    public class ConnectionWarp
    {
        private final Connection con;
        private boolean flag;

        ConnectionWarp(Connection con)
        {
            this.con = con;
            this.flag = false;
        }

        public Connection getCon()
        {
            return this.con;
        }

        public ConnectionWarp changeStatus()
        {
            this.flag = !this.flag;
            return this;
        }

        public void close()
        {
            this.flag = false;
        }

    }
}
