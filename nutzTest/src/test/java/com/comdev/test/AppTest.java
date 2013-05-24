package com.comdev.test;

import VO.VOt1;
import com.alibaba.druid.pool.DruidDataSource;
import com.comdev.ut.PropertiesUT;
import com.me.ut.string.Dump;
import nutzTest.VOTest2;
import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

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

    public void testTrans()
    {
        Trans.exec(new Atom()
        {
            @Override
            public void run()
            {
                dao.insert(VOTest2.me());
//                if (1 == 1)
//                {
//                    throw new RuntimeException("xxx");
//                }

                List list = dao.query(VOTest2.class, null);
                logger.debug(list.toString());
                //这种情况多不多？
            }
        });
    }


    public void testCreataTable()
    {
        dao.create(VOTest2.class, true);
//        Date d1 = new Date();
//        for (int i = 1; i <= 1000; i++)
//        {
//            dao.insert(VOTest2.me());
//        }
//        Date d2 = new Date();
//        System.out.println(d2.getTime() - d1.getTime());
    }

    /**
     * 通过简单查询，关注如何执行的查询
     */
    public void testQuery()
    {
        List<Record> list = dao.query("t_news", Cnd.where("objid_", "like", "2e43"));
        Dump.print(list);
    }


    public void test04()
    {

        dao.create(VOt1.class, true);

        for (int i = 1; i <= 1000; i++)
        {
            dao.insert(VOt1.me());
        }

    }

    public static void main(String[] args)
    {
        AppTest test = new AppTest();
<<<<<<< HEAD
        test.testTrans();
=======
        test.test04();
    }
>>>>>>> 1fd49740445711acfb58a9190b845c17deabb19d

    }

}
