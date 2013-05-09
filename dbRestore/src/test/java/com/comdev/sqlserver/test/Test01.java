package com.comdev.sqlserver.test;

import com.comdev.sqlserver.SqlServer_bakrestore;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午3:28
 */
public class Test01
{
    private  static  final Logger logger=Logger.getLogger(Test01.class);

    /**
     * 测试sqlserver的附加操作
     */
    @Test
    public  void test01()
    {
        SqlServer_bakrestore  sql=new SqlServer_bakrestore();
        sql.Restore("bea1","c:\\bea_Data.MDF","c:\\bea_log.ldf");
    }


}
