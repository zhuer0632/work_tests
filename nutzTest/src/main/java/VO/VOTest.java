package VO;

import com.alibaba.druid.pool.DruidDataSource;
import com.comdev.ut.PropertiesUT;
import com.me.ut.string.StringUT;
import org.nutz.dao.entity.Record;
import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.impl.NutDao;
import org.nutz.lang.Files;

import java.util.Date;
import java.util.List;

/**
 * User: zhu
 * Date: 13-5-11
 * Time: 下午11:23
 */
@Table("t_test")
public class VOTest
{
    @Name
    private String objid;

    @Column
    @ColDefine( type = ColType.TEXT)//255个字节=11111111
    private String  fiedl1;

    public String getObjid()
    {
        return objid;
    }

    public void setObjid(String objid)
    {
        this.objid = objid;
    }


    public static void main(String[] args)
    {
        NutDao dao = new NutDao();
        DruidDataSource druid = new DruidDataSource();
        druid.setUrl(PropertiesUT.get_url());
        druid.setUsername(PropertiesUT.get_username());
        druid.setPassword(PropertiesUT.get_password());
        druid.setMaxWait(PropertiesUT.get_maxconn());
        druid.setMaxActive(PropertiesUT.get_minconn());
        druid.setInitialSize(2);
        dao.setDataSource(druid);

        dao.create(VOTest.class, true);

        dao.insert(VOTest.me());
//
//        List<Record> list = dao.query("t_test", null);
//        System.out.println(list.get(0).get("fiedl1"));
    }

    private static VOTest me()
    {
        VOTest vo = new VOTest();
        vo.setObjid(StringUT.getUUID());
        vo.setFiedl1(getText());
        return vo;
    }

    private static String getText()
    {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<65536;i++)
        {
            stringBuilder.append("1");
        }
        System.out.println(stringBuilder.toString().getBytes().length);
        return stringBuilder.toString();
    }


    private static int getInt()
    {
        return 2147483647;
    }

    private static float getFloat()
    {
//        return  Float.MAX_VALUE;
        return  Float.MIN_VALUE;
    }



    private static Date getadata3()
    {
        Date d = new Date();
        System.out.println(d.getTime());
        return d;
    }

    private void setFiedl1(String getadata)
    {
        this.fiedl1 = getadata;
    }

    /**
     * 取得大文本
     *
     * @return
     */
    private static String getdata()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 65535; i++)
        {
            stringBuilder.append("1");
        }
        return stringBuilder.toString();
    }

    /**
     * 取得二进制
     *
     * @return
     */
    private static byte[] getadata2()
    {
        StringBuilder stringBuilder = new StringBuilder();
//        byte[] bs =  new byte[8388565];

        byte[] bs = Files.read("c:\\data.txt").getBytes();
        return bs;
    }

}
