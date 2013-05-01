package dbutiles_test;


import com.dbutils.QueryHelper;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 

{
    @Test
    public void test01()
    {
            Object[] objs= QueryHelper.query_Array("select * from t_news");

            System.out.println(objs);
    }


}
