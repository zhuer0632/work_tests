package VO;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

/**
 * User: zhu
 * Date: 13-5-11
 * Time: 下午10:19
 */
public class Pet
{

    @Name
    @Column
    @ColDefine(width = 100)
    private  String  name;

    @Column
    @ColDefine(width = 100)
    private String  color;

    @Column
    private   int  age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
