package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Author: gnoloahs
 * Date: 2013-04-08
 * Time: 下午5:14
 */
public class info
{


    public  void test()
    {
        Connection conn=null;
        try
        {

            //打开事务
            conn.setAutoCommit(false);

            //插入添加记录
            insert("insert  t1(id,f1)values('1','aaa') ");

            //查询记录并显示
            String f1=query("select f1 from t1 where id='1'");

            //提交事务
            conn.commit();
        }
        catch (Exception e)
        {

        }


    }

    private String query(String s)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private void insert(String s)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

}
