package cn.evole.mods.elona.common.save;

import cn.evole.mods.elona.Static;
import cn.evole.mods.elona.api.save.sql.base.Table;
import cn.evole.mods.elona.api.save.sql.database.Database;
import cn.evole.mods.elona.api.save.sql.database.SQLiteDatabase;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

/**
 * Name: Elona / PlayerDataSave
 * Author: cnlimiter
 * CreateTime: 2023/9/3 22:24
 * Description:
 */

@Mod.EventBusSubscriber
public class GameDataSave {

    public static Database db = new SQLiteDatabase(Static.MOD_ID, "ELONA", FMLPaths.GAMEDIR.relative().resolve("elona_data").toString());

    public static Table PLAYER_DATA;
    @SubscribeEvent
    public static void dataInit(ServerStartedEvent event){
        db.open();
        PLAYER_DATA = db.getTable("PLAYER") != null ? db.getTable("PLAYER") : db.createTable("PLAYER");
    }

    @SubscribeEvent
    public static void dataSave(ServerStoppedEvent event){
        db.close();
    }

}
