package com.comdev.sqlserver;

import com.comdev.db.DbKit;
import com.comdev.db.ddl.SqlServerDBMeta;
import com.me.ut.exe.RunExe;
import com.me.ut.file.FileUT;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Author: gnoloahs
 * Date: 2013-04-03
 * Time: 下午3:26
 */
public class SqlServer_bakrestore
{

    private static final Logger logger = Logger.getLogger(SqlServer_bakrestore.class);

    /**
     * 根据指定的配置文件进行备份
     */
    public void bak(String bakfile)
    {
        //BACKUP DATABASE  pri  TO  DISK='c:\1.bak'
        logger.debug("no implement!!!");
    }

    /**
     * 根据指定的备份文件如  bea_Data.MDF， bea_log.ldf 进行附加
     */
    public void Restore(String dbName, String dbBak_datafile, String dbBak_logfile)
    {

        if (!new File(dbBak_datafile).exists() || !new File(dbBak_logfile).exists())
        {
            logger.debug("指定的数据库备份文件不存在");
            return;
        }

        //检查数据库是否已经存在
        SqlServerDBMeta sqlServerDBMeta = new SqlServerDBMeta();

        //数据库
        if (!sqlServerDBMeta.checkDbConfig())
        {
                logger.debug("数据库连接失败");
            return;
        }

        List<String> dbs = sqlServerDBMeta.getdbNames();
        if (dbs.contains(dbName))
        {
            logger.debug("指定的数据库已经存在" + dbName);
            return;
        }

        //首先把数据库文件复制到  C:\Program Files\Microsoft SQL Server\MSSQL\Data\ 目录下
        //然后进行附加
        new File("C:\\Program Files\\Microsoft SQL Server\\MSSQL\\Data\\").mkdirs();

        String target_data = "C:\\Program Files\\Microsoft SQL Server\\MSSQL\\Data\\" + dbName + "_data.mdf";
        String target_log = "C:\\Program Files\\Microsoft SQL Server\\MSSQL\\Data\\" + dbName + "_log.mdf";
        if (new File(target_data).exists())
        {
            logger.debug("指定的数据库已经存在" + dbName+"data文件已经存在");
            return;
        }
        if (new File(target_log).exists())
        {
            logger.debug("指定的数据库已经存在" + dbName+"log文件已经存在");
            return;
        }

        try
        {
            logger.debug("正在拷贝文件from:" + dbBak_datafile + "  =to:" + target_data);
            FileUtils.copyFile(new File(dbBak_datafile), new File(target_data
            ));
            logger.debug("正在拷贝文件from:" + dbBak_logfile + "  =to:" + target_log);
            FileUtils.copyFile(new File(dbBak_logfile), new File(target_log));
        } catch (IOException e)
        {
        }
        //
        logger.debug("正在执行附加操作");
        String sql = "exec sp_attach_db @dbname='" + dbName + "', @filename1=N'" + target_data + "',@filename2=N'" + target_log + "'";

        //执行特殊sql语句
        DbKit.executeNaviteSQL(sql);

        logger.debug("数据库附加完毕" + dbName);

    }
}
