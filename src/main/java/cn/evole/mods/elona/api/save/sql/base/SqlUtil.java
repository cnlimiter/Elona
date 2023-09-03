package cn.evole.mods.elona.api.save.sql.base;

import com.google.gson.JsonParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import static cn.evole.mods.elona.Static.MOD_ID;

/**
 * Name: Elona / SqlUtil
 * Author: cnlimiter
 * CreateTime: 2023/9/3 22:08
 * Description:
 */

public class SqlUtil {
    public static final JsonParser jsonParser = new JsonParser();

    public static void log(Level level, String message) {
        LogManager.getLogger().log(level, "[" + MOD_ID + ":SQL" + "] " + message);
    }
}
