package cn.evole.mods.elona.init.handler;

import cn.evole.mods.elona.Static;
import cn.evole.mods.elona.common.core.player.PlayerData;
import cn.evole.mods.elona.common.core.player.PlayerDataManager;
import cn.evole.mods.elona.common.net.pkt.PlayerDataPkt;
import cn.evole.mods.elona.util.PlayerUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

/**
 * Name: Elona-forged / SaveEventHandler
 * Author: cnlimiter
 * CreateTime: 2023/9/8 21:48
 * Description:
 */
@Mod.EventBusSubscriber
public class SaveEventHandler {

    @SubscribeEvent
    public static void onPlayerFileSaved(PlayerEvent.SaveToFile event) {
        //玩家保存数据时，保存模组玩家相关数据
        PlayerData.serialize(Static.player_data, event.getPlayerUUID());
    }

    @SubscribeEvent
    public static void onPlayerFileLoad(PlayerEvent.LoadFromFile event) {
        //玩家加载数据时，加载模组玩家相关数据
        Player player = event.getEntity();
        PlayerData playerData = PlayerData.deserialize(Static.player_data, event.getPlayerUUID());
        PlayerUtil.onClone(player, playerData, false);
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        //玩家重生时，加载模组玩家相关数据
        Player player = event.getEntity();
        PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
        PlayerUtil.onClone(player, playerData, event.isWasDeath());
    }

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Level level = event.getLevel();
        Entity entity = event.getEntity();
        if (!level.isClientSide()) {
            //todo 同步除玩家外其他实体数据
        }
        else if (!level.isClientSide() && entity instanceof ServerPlayer) {
            //同步模组玩家数据到客户端
            PlayerDataPkt pack = new PlayerDataPkt("elona.player.status.async", PlayerDataManager.get(entity.getStringUUID()));
            NetWorkHandler.PLAYER_DATA.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) entity), pack);
        }
    }
}
