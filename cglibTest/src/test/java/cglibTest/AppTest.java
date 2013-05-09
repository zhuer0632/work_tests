package cglibTest;


import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    private static final Logger logger = Logger.getLogger(AppTest.class);

    @Test
    public void test01()
    {

        //普通调用
//        logger.debug("使用普通方式");
//        App app = new App();
//        String out = app.insert("abc");
//        logger.debug(out);


        //代理方式的调用
        App app = new App();

        logger.debug("使用代理方式");
        AppProxy proxy = new AppProxy();
        App proxyapp = proxy.getInstance(app);
        String out2 = proxyapp.insert2("abc");
        logger.debug(out2);



    }


}
