package nutzTest;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * User: zhu
 * Date: 13-5-7
 * Time: 下午11:38
 */

@Table("t_test2")
public class VOTest2
{

    @Id
    private int id;

    @Column
    @ColDefine(width = 10)
    private String f1;

    @Column
    private Date date;

    public String getF1()
    {
        return f1;
    }

    public void setF1(String f1)
    {
        this.f1 = f1;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public static VOTest2 me()
    {
        VOTest2 vo = new VOTest2();
        vo.setDate(new Date());
        vo.setF1("123");
        return vo;
    }
}
