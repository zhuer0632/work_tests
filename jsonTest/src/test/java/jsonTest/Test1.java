package jsonTest;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: gnoloahs
 * Date: 2013-04-16
 * Time: 上午10:23
 */
public class Test1
{

    private static final Logger logger = Logger.getLogger(Test1.class);

    @Test
    public void test01()
    {

        String s = "" +
                "{[" +
                "username_:'a'," +
                "username_:'b'," +
                "username_:'c'," +
                "username_:'d'," +
                "]}" +
                "" +
                "";

//        List list = JSON.parseArray(s);

        List l = new ArrayList<String>();
        l.add("as");
        l.add("as1");
        l.add("as2");
        l.add("as3");

        String out = JSON.toJSONString(l);
        System.out.println(out);
        logger.debug(out);


    }


    @Test
    public void test02()
    {
        String s = "[\"as\",\"as1\",\"as2\",\"as3\"]";

        List<String> out = JSON.parseArray(s, String.class);

        logger.debug(out);

    }


    @Test
    public void test03()
    {
        Map map = new HashMap<String, String>();

        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");

        String out = JSON.toJSONString(map);
        logger.debug(out);

    }




}
