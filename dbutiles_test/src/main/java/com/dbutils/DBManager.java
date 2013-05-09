package com.dbutils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DBManager
{
    private final static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    private final static ThreadLocal<Integer> count = new ThreadLocal<Integer>();
    private static DataSource dataSource;
    private static Connection conn;
    static
    {
        DruidDataSource druid = new DruidDataSource();

        String path=DBManager.class.getResource("/").getPath()+ File.separator+"db.properties";
        druid.setUrl(PropertiesUT.getProV(path,"jdbc.url"));
        druid.setUsername(PropertiesUT.getProV(path,"jdbc.username"));
        druid.setPassword(PropertiesUT.getProV(path,"jdbc.password"));
        druid.setMaxWait(Integer.valueOf(PropertiesUT.getProV(path,"jdbc.maxconn")));// 最多创建多少链接
        druid.setMaxActive(Integer.valueOf(PropertiesUT.getProV(path,"jdbc.minconn")));// 最少保留多少链接
        druid.setInitialSize(2);// 没有连接的时候一次创建多少
        dataSource = (DataSource) druid;
    }

    public final static DataSource getDataSource()
    {
        return dataSource;
    }


    public final static void closeDataSource()
    {
        try
        {
            dataSource.getClass().getMethod("close").invoke(dataSource);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unabled to destroy DataSource!!!", e);
        }
    }
    
    public final static Connection getConnection2()
    {
        try
        {
            if (conn == null || conn.isClosed())
            {
                conn = dataSource.getConnection();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unabled to close Connection!!!", e);
        }
        return conn;
    }
    
    public final static Connection getConnection()
    {
        Connection conn = conns.get();
        try
        {
            if (conn == null || conn.isClosed())
            {
                conn = dataSource.getConnection();
                conns.set(conn);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unabled to close Connection!!!", e);
        }
        return conn;
    }


    // 用于事务
    public final static Connection getConn()
    {
        return conns.get();
    }


    // 用于事务
    public final static void setConn(Connection conn)
    {
        conns.set(conn);
    }


    /**
     * 关闭连接
     */
    public final static void closeConnection()
    {
        Connection conn = conns.get();
        try
        {
            // count.get()==null的时候没有事务嵌套
            if (conn != null && !conn.isClosed() && count.get() == null)
            {
                conn.setAutoCommit(true);
                conn.close();
                conns.set(null);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Unabled to close connection!!!", e);
        }

    }


    // 记录事务的序号
    public static void setCount(Integer i)
    {
        count.set(i);
    }


    // 记录事务的序号
    public static Integer getCount()
    {
        return count.get();
    }

}