package nutzTest;

import VO.News;
import com.alibaba.druid.pool.DruidDataSource;
import com.comdev.ut.PropertiesUT;
import org.nutz.dao.impl.NutDao;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

/**
 * Hello world!
 */
public class App
{

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

    //测试事务是否能够回滚，在事务代码里。最后还没提交前，断掉程序

    public static void main(String[] args)
    {
        App app = new App();
        app.test01();
    }

    private void test01()
    {

        Trans.exec(new Atom()
        {
            @Override
            public void run()
            {
                News  news=new News();
                dao.insert(news);
                News    news2=   dao.fetch(News.class,news.getObjid_());
                System.out.println("执行结束");
            }
        });


    }


}

