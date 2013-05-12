package com.comdev.test;

import org.nutz.lang.Files;

/**
 * User: zhu
 * Date: 13-5-12
 * Time: 上午1:58
 */
public class CreateFile
{
    public  static  void    main(String[]args)
    {
        CreateFile  c=new CreateFile();
        c.test01();
    }

    private void test01()
    {
        byte[]bs=new byte[8388565];
        Files.write("c:\\data.txt",bs);
    }


}
