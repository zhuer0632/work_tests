package com.comdev.mysql;

import com.comdev.db.ddl.MysqlDBMeta;
import com.comdev.ut.PropertiesUT;
import com.me.ut.exe.RunExe;
import com.me.ut.string.ConnUrlAnalyse;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Author: gnoloahs
 * Date: 2013-03-29
 * Time: 上午11:39
 */
public class Mysql_bakrestore
{

    private static final Logger logger = Logger.getLogger(Mysql_bakrestore.class);

    /**
     * 根据指定的配置文件进行备份
     */
    public void bak(String bakfile)
    {
        //执行mysqldump命令
        try
        {
            String path = Mysql_bakrestore.class.getResource("/").getPath();
            path = path.substring(1, path.length());
            logger.debug("mysqldump.exe 的位置：" + path);
            RunExe.ec("cmd /c " + path + "mysqldump -h" + ConnUrlAnalyse.getIP(PropertiesUT.get_url()) + " -u" + PropertiesUT.get_username() + " -p" + PropertiesUT.get_password() +  "  "+ ConnUrlAnalyse.getDbName(PropertiesUT.get_url()) +">" + bakfile );
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 根据指定的文件执行
     *
     * @param bakfile
     */
    public void Restore(String bakfile)
    {
        if (!new File(bakfile).exists())
        {
            logger.debug("指定的文件不存在" + bakfile);
            return;
        }

        //检查数据库是否存在
        MysqlDBMeta mysqlDBMeta = new MysqlDBMeta();
        if (!mysqlDBMeta.checkDbConfig())
        {
            logger.debug("db.properties配置不正确");
            return;
        }


        try
        {
            String path = Mysql_bakrestore.class.getResource("/").getPath();
            path = path.substring(1, path.length());
            logger.debug("mysql.exe 的位置：" + path);
            RunExe.ec("cmd /c " + path + "mysql -h" + ConnUrlAnalyse.getIP(PropertiesUT.get_url()) + " -u" + PropertiesUT.get_username() + " -p" + PropertiesUT.get_password() + " " + ConnUrlAnalyse.getDbName(PropertiesUT.get_url()) + "<" + bakfile );
        } catch (Exception e)
        {

        }


    }


}
