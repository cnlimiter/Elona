package cn.evole.mods.elona.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Name: Elona-forged / FileUtil
 * Author: cnlimiter
 * CreateTime: 2023/9/5 23:31
 * Description:
 */

public class FileUtil {
    public static void checkFolder(Path folder){
        if (!folder.toFile().isDirectory()) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
