package VO;

import com.me.ut.string.StringUT;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * User: zhu
 * Date: 13-5-11
 * Time: 下午10:19
 */
@Table("t_news")
public class News
{

    @Name
    @Column
    @ColDefine(width = 32)
    private String objid_;

    @Column
    @ColDefine(width = 100)
    private String title_;

    @Column
    @ColDefine(width = 100)
    private String content_;

    @Column
    private Date modifyDate_;

    @Column
    private int  order_;



    public News()
    {
         setObjid_(StringUT.getUUID());
        setTitle_("aaa");
        setContent_("中文");
        setModifyDate_(new Date());
        setOrder_(111);
    }

    public String getObjid_()
    {
        return objid_;
    }

    public void setObjid_(String objid_)
    {
        this.objid_ = objid_;
    }

    public String getTitle_()
    {
        return title_;
    }

    public void setTitle_(String title_)
    {
        this.title_ = title_;
    }

    public String getContent_()
    {
        return content_;
    }

    public void setContent_(String content_)
    {
        this.content_ = content_;
    }

    public Date getModifyDate_()
    {
        return modifyDate_;
    }

    public void setModifyDate_(Date modifyDate_)
    {
        this.modifyDate_ = modifyDate_;
    }

    public int getOrder_()
    {
        return order_;
    }

    public void setOrder_(int order_)
    {
        this.order_ = order_;
    }
}
