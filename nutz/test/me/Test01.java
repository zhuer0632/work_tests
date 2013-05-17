package me;

import com.alibaba.druid.pool.DruidDataSource;
import org.nutz.dao.impl.NutDao;

/**
 * User: zhu
 * Date: 13-5-18
 * Time: 上午1:44
 */
public class Test01
{

    private static NutDao dao;


    static
    {
        //初始化dao的配置文件
        dao = new NutDao();

        DruidDataSource druid = new DruidDataSource();
        druid.setUrl("jdbc:mysql://127.0.0.1:3306/db_test_nutz?charachterEncoding=UTF-8;");
        druid.setUsername("root");
        druid.setPassword("root");
        druid.setMaxWait(20);
        druid.setMaxActive(50);
        druid.setInitialSize(2);
        dao.setDataSource(druid);

    }

    public static void main(String[] args)
    {
        dao.create(VOTest.class,true);
        dao.insert(VOTest.me());

    }


}
