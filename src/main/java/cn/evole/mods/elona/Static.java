package cn.evole.mods.elona;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.io.File;

/**
 * Name: Elona / Static
 * Author: cnlimiter
 * CreateTime: 2023/9/3 5:02
 * Description:
 */

public class Static {
    public static final String MOD_ID = "elona";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static File ELONA_FOLDER = FMLPaths.GAMEDIR.relative().resolve("elona_data").toFile();


}
