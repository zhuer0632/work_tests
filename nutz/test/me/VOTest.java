package me;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * User: zhu
 * Date: 13-5-18
 * Time: 上午1:54
 */
@Table("t1")
public class VOTest
{
    @Column
    @Name
    private String uid;

    @Column
    private int age;

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public static VOTest me()
    {
        VOTest vo = new VOTest();
        vo.setUid("abc");
        vo.setAge(111);
        return vo;
    }
}
