package com.dbutils;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


/**
 * jdbc -- 辅助类
 */
public class QueryHelper
{

    public static int threadCount = 0;
    public static boolean threadHasStart = false;

    private static QueryRunner run = new QueryRunner();


    /**
     * 
     * 执行INSERT/UPDATE/DELETE语句(删除最好别用该方法，执行会将数据库数据彻底删除)
     * 
     * @param sql
     * @param params
     * @return
     */
    public static int execute(String sql, Object... params)
    {
        int out = 0;
        try
        {
            out = run.update(DBManager.getConnection(), sql, params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }

        return out;
    }


    public static int execute2(String sql, Object... params)
    {
        int out = 0;
        try
        {
            out = run.update(DBManager.getConnection2(), sql, params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 批量执行指定的SQL语句
     * 
     * @return
     */
    public static int[] batch(String sql, Object[][] params)
    {
        int[] out = null;
        try
        {
            out = run.batch(DBManager.getConnection(), sql, params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 第一条记录转换成Object数组
     * 
     * @param sql
     * @param params
     * @return
     */
    public static Object[] query_Array(String sql, Object... params)
    {
        Object[] out = null;
        try
        {
            out = run.query(DBManager.getConnection(), sql, new ArrayHandler(),
                    params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 所有记录都转换成Object[],然后存放在List中
     * 
     * @param sql
     * @param params
     * @return
     */
    public static List<Object[]> query_ArrayList(String sql, Object... params)
    {
        List<Object[]> out = null;
        try
        {
            out = run.query(DBManager.getConnection(), sql,
                    new ArrayListHandler(), params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 查询第一条记录的T类型作为返回结果
     * 
     * @param <T>
     * @param beanClass
     * @param sql
     * @param params
     * @return
     */
    public static <T> T query_Bean(Class<T> beanClass, String sql,
            Object... params)
    {
        T out = null;
        try
        {
            out = (T) run.query(DBManager.getConnection(), sql,
                    new BeanHandler<T>(beanClass), params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 查询所有的记录的T类型
     * 
     * @param <T>
     * @param beanClass
     * @param sql
     * @param params
     * @return
     */
    public static <T> List<T> query_BeanList(Class<T> beanClass, String sql,
            Object... params)
    {
        List<T> out = null;
        try
        {
            out = run.query(DBManager.getConnection(), sql,
                    new BeanListHandler<T>(beanClass), params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }



    /**
     * 第一条记录作为一个Map
     * 
     * @param sql
     * @param params
     * @return
     */
    public static Map<String, Object> query_Map(String sql, Object... params)
    {
        Map<String, Object> out = null;
        try
        {
            out = run.query(DBManager.getConnection(), sql, new MapHandler(),
                    params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 每条记录作为一个Map
     * 
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> query_MapList(String sql,
            Object... params)
    {
        List<Map<String, Object>> out = null;
        try
        {
            out = run.query(DBManager.getConnection(), sql,
                    new MapListHandler(), params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 
     * 查询左上角的一条记录
     * 
     * @param sql
     * @param params
     * @return
     */
    public static Object query_Scalar(String sql, Object... params)
    {
        Object out = null;
        try
        {
            out = run.query(DBManager.getConnection(), sql,
                    new ScalarHandler(), params);
        }
        catch (SQLException e)
        {
            DbUtils.printStackTrace(e);
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.closeConnection();
        }
        return out;
    }


    /**
     * 多个线程互不干扰，每个线程独立的数据库连接
     */
    public static void tx(Atom... atoms)
    {
        tx(Connection.TRANSACTION_READ_COMMITTED, atoms);
    }


    /**
     * 所有线程公用一个数据库连接
     */
    public static void tx2(Atom... atoms)
    {
        tx2(Connection.TRANSACTION_READ_COMMITTED, atoms);
    }


    private static void tx2(int level, Atom[] atoms)
    {
        _begain2(level);
        for (Atom atom : atoms)
        {
            atom.run();
        }
        _commit2();
    }


    private static void tx(int level, Atom[] atoms)
    {
        if (null == atoms)
            return;
        int num = DBManager.getCount() == null ? 0 : DBManager.getCount();
        try
        {
            _begain(level);
            for (Atom atom : atoms)
            {
                atom.run();
            }
            _commit();
        }
        catch (Exception e)
        {
            _rollback(num);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally
        {
            _depose();
        }
    }


    private static void _depose()
    {
        if (DBManager.getCount() == 0)
        {
            try
            {
                DBManager.getConn().close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                DBManager.setConn(null);
                DBManager.setCount(null);
            }
        }
    }


    private static void _rollback(int num)
    {
        DBManager.setCount(num);
        if (DBManager.getCount() == 0)// 最后一个事务提交后才能回滚，根本没有提交提交就进行回滚则出错
        {
            try
            {
                DBManager.getConn().rollback();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private static void _commit()
    {
        DBManager.setCount(DBManager.getCount() - 1);
        if (DBManager.getCount() == 0)
        {
            try
            {
                DBManager.getConn().commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private static void _commit2()
    {
        if (threadHasStart == true && threadCount == 0)
        {
            try
            {
                DBManager.getConn().commit();
                DBManager.getConn().close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                DBManager.getConn().rollback();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    private static void _begain2(int level)
    {
        if (null == DBManager.getConn())
        {
            // 第一个连接要从数据源取得
            Connection conn;
            try
            {
                conn = DBManager.getConnection2();
                conn.setTransactionIsolation(level);
                conn.setAutoCommit(false);// 打开事务
                DBManager.setConn(conn);
                DBManager.setCount(0);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    private static void _begain(int level)
    {
        if (null == DBManager.getConn())
        {
            // 第一个连接要从数据源取得
            Connection conn;
            try
            {
                conn = DBManager.getDataSource().getConnection();
                conn.setTransactionIsolation(level);
                conn.setAutoCommit(false);// 打开事务
                DBManager.setConn(conn);
                DBManager.setCount(0);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        DBManager.setCount(DBManager.getCount() + 1);
    }
}
