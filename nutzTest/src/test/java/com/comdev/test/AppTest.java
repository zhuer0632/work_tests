package com.comdev.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.comdev.ut.PropertiesUT;
import nutzTest.VOTest2;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest

{
    private static final Logger logger = Logger.getLogger(AppTest.class);

    private static NutDao dao;


    static
    {
        //初始化dao的配置文件
        dao = new NutDao();

        DruidDataSource druid = new DruidDataSource();
        druid.setUrl(PropertiesUT.get_url());
        druid.setUsername(PropertiesUT.get_username());
        druid.setPassword(PropertiesUT.get_password());
        druid.setMaxWait(PropertiesUT.get_maxconn());// 最多创建多少链接
        druid.setMaxActive(PropertiesUT.get_minconn());// 最少保留多少链接
        druid.setInitialSize(2);// 没有连接的时候一次创建多少
        dao.setDataSource(druid);

    }

    public void test01()
    {
        Trans.exec(new Atom()
        {
            @Override
            public void run()
            {

                dao.insert("t1", Chain.make("id_", "111").add("f1_", "22222"));

                List list = dao.query("t1", null);
                logger.debug(list.toString());

                //这种情况多不多？
            }
        });
    }


    public void test03()
    {

        dao.create(VOTest2.class, true);

        Date d1 = new Date();
        for (int i = 1; i <= 1000; i++)
        {
            dao.insert(VOTest2.me());
        }

        Date d2 = new Date();

        System.out.println(d2.getTime() - d1.getTime());

    }


    public static void main(String[] args)
    {
        AppTest test = new AppTest();
        test.test03();
    }


}
