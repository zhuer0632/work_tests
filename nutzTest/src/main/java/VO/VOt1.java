package VO;

import com.me.ut.string.StringUT;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * User: zhu
 * Date: 13-5-13
 * Time: 上午12:38
 */
@Table("t1")
public class VOt1
{
    @Name
    @ColDefine(width = 32)
    private String id_;

    @ColDefine(width = 100)
    @Column
    private String f1_;

    public String getId_()
    {
        return id_;
    }

    public void setId_(String id_)
    {
        this.id_ = id_;
    }

    public String getF1_()
    {
        return f1_;
    }

    public void setF1_(String f1_)
    {
        this.f1_ = f1_;
    }

    public static VOt1 me()
    {
        VOt1 v=new VOt1();
        v.setF1_("hahahah");
        v.setId_(StringUT.getUUID());
        return  v;
    }
}
