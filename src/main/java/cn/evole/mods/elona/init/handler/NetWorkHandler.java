package cn.evole.mods.elona.init.handler;

import cn.evole.mods.elona.Static;
import cn.evole.mods.elona.api.init.handler.NetBaseHandler;
import cn.evole.mods.elona.common.net.pkt.PlayerDataPkt;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Name: Elona-forged / NetWorkHandler
 * Author: cnlimiter
 * CreateTime: 2023/9/10 18:01
 * Description:
 */

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetWorkHandler {


    public static final NetBaseHandler PLAYER_DATA = new NetBaseHandler(new ResourceLocation(Static.MOD_ID, "player_data"));

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PLAYER_DATA.register(PlayerDataPkt.class, new PlayerDataPkt());
        });

    }


}
