package com.stark.jdbc.dao;

import com.stark.jdbc.entity.Student;
import com.stark.jdbc.pool.ConnectionPool;
import com.stark.jdbc.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by Strak on 2017/4/22.
 * 用于构建增删改查的Dao类
 */

class Dao<T>
{
    private final Class clazz; //获取泛型类型
    private final String entityName;
    private final ConnectionPool pool;

    Dao()
    {
        super();
        Class c = this.getClass();
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.clazz = (Class) p[0];
            entityName = this.clazz.getSimpleName();
        } else {
            this.clazz = Student.class;
            entityName = this.clazz.getSimpleName();
        }
        this.pool = new ConnectionPool();
    }

    /**
     * 获取Connection接口
     */
    private ConnectionPool.ConnectionWarp getConnection()
    {
        return this.pool.getConnection();
    }

    public void save(T entity) throws SQLException, IllegalAccessException
    {

        /*使用Statement不安全使用
        Connection connection = getConnection();
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        StringBuilder fieldNames = new StringBuilder("(");
        StringBuilder valueNames = new StringBuilder("(");
        try {
            connection.setAutoCommit(false);
            //拼接SQL
            sql.append(entityName).append("");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                fieldNames.append(field.getName()).append(",");
                field.setAccessible(true);
                String value = field.get(entity).toString();
                if (value == null) {
                    valueNames.append("Null,");
                } else {
                    valueNames.append("'").append(value).append("',");
                }
            }
            sql.append(fieldNames.deleteCharAt(fieldNames.length() - 1)).append(") VALUES ")
                    .append(valueNames.deleteCharAt(valueNames.length() - 1)).append(')');
            Statement statement = connection.createStatement();
            System.out.println(sql.toString());
            statement.execute(sql.toString());
            connection.commit();
            connection.close();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        ConnectionPool.ConnectionWarp connection = getConnection();
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        StringBuilder fieldNames = new StringBuilder("(");
        StringBuilder valueNames = new StringBuilder("(");
        connection.getCon().setAutoCommit(false);
        //拼接SQL
        sql.append(entityName).append("");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.append(field.getName()).append(",");
            field.setAccessible(true);
            valueNames.append("?,");
        }
        sql.append(fieldNames.deleteCharAt(fieldNames.length() - 1)).append(") VALUES ")
                .append(valueNames.deleteCharAt(valueNames.length() - 1)).append(')');

        PreparedStatement preparedStatement = connection.getCon().prepareStatement(sql.toString());

        for(int i = 1;i<=fields.length;i++)
        {
            Field field = fields[i-1];
            Object o = field.get(entity);
            if(o==null)
            {
                preparedStatement.setNull(i,1);
            }
            else if (o instanceof Integer)
            {
                preparedStatement.setInt(i,Integer.valueOf(o.toString()));
            }
            else if (o instanceof Boolean)
            {
                preparedStatement.setBoolean(i,Boolean.valueOf(o.toString()));
            }
            else if (o instanceof Double)
            {
                preparedStatement.setDouble(i,Double.valueOf(o.toString()));
            }
            else if (o instanceof Date)
            {
                preparedStatement.setDate(i, (java.sql.Date) o);
            }
            else if (o instanceof String)
            {
                preparedStatement.setString(i,o.toString());
            }

        }
        preparedStatement.execute();
        connection.getCon().commit();
        connection.close();
    }


}
