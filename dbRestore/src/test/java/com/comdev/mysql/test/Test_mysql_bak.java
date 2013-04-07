package com.comdev.mysql.test;

import com.comdev.mysql.Mysql_bakrestore;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午8:05
 */
public class Test_mysql_bak
{

    private Logger logger = Logger.getLogger(Test_mysql_bak.class);

    @Test
    public void test01()
    {
        Mysql_bakrestore mysql = new Mysql_bakrestore();
        mysql.bak("c:\\sql.bak");
    }

    @Test
    /**
     * 测试还原mysql
     */
    public void test02()
    {
        String file = "c:\\sql.bak";
        Mysql_bakrestore mysql = new Mysql_bakrestore();
        mysql.Restore(file);
    }


}
