package cn.evole.mods.elona;

import com.mojang.logging.LogUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.minecraftforge.fml.loading.FMLPaths;
import org.noear.wood.DbContext;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.io.File;

/**
 * Name: Elona / Static
 * Author: cnlimiter
 * CreateTime: 2023/9/3 5:02
 * Description:
 */

public class Static {

    public static final String MOD_ID = "elona";
    public static final Logger LOGGER = LogUtils.getLogger();

    //elona数据文件夹
    public static File ELONA_FOLDER = FMLPaths.GAMEDIR.relative().resolve("elona_data").toFile();

    //Hikari配置
    public static HikariConfig dataConfig = new HikariConfig();

    static {
        dataConfig.setPoolName("SQLiteConnectionPool");
        dataConfig.setDriverClassName("org.sqlite.JDBC");
        dataConfig.setJdbcUrl("jdbc:sqlite:" + ELONA_FOLDER.getAbsolutePath() + "\\" + "test" + ".db");
        dataConfig.setAutoCommit(false);
        dataConfig.setMinimumIdle(8);
        dataConfig.setMaximumPoolSize(32);
    }

    //数据源
    public static final DataSource dataSource = new HikariDataSource(dataConfig);

    //玩家数据
    public static final DbContext player_data = new DbContext("player_data", dataSource);



}
