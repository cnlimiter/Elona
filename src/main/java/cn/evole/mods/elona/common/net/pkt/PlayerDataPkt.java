package cn.evole.mods.elona.common.net.pkt;

import cn.evole.mods.elona.api.common.net.IPacket;
import cn.evole.mods.elona.common.core.player.MainAdditions;
import cn.evole.mods.elona.common.core.player.MainAttributes;
import cn.evole.mods.elona.common.core.player.MainStats;
import cn.evole.mods.elona.common.core.player.PlayerData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Name: Elona-forged / PlayerDataPkt
 * Author: cnlimiter
 * CreateTime: 2023/9/6 19:54
 * Description:
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayerDataPkt extends IPacket<PlayerDataPkt> {
    private final String message;
    private final String playerUUID;
    //玩家主能力
    private MainAttributes attributes;
    //玩家主状态
    private MainStats stats;
    //玩家主信息
    private MainAdditions additions;
    public PlayerDataPkt(String message) {
        this.message = message;
        this.playerUUID = "null";
        this.attributes = new MainAttributes();
        this.stats = new MainStats();
        this.additions = new MainAdditions();
    }

    public PlayerDataPkt(String message, PlayerData data) {
        this.message = message;
        this.playerUUID = data.getPlayerUUID();
        this.attributes = data.getAttributes();
        this.stats = data.getStats();
        this.additions = data.getAdditions();
    }
    @Override
    public PlayerDataPkt read(FriendlyByteBuf buf) {
        return new PlayerDataPkt(buf.readUtf(Short.MAX_VALUE), buf.readUtf(Short.MAX_VALUE)
                , MainAttributes.readBuf(buf), MainStats.readBuf(buf), MainAdditions.readBuf(buf));
    }

    @Override
    public void write(PlayerDataPkt msg, FriendlyByteBuf buf) {
        buf.writeUtf(this.message);
        buf.writeUtf(this.playerUUID);
        attributes.writeBuff(buf);
        stats.writeBuff(buf);
        additions.writeBuff(buf);
    }

    @Override
    public void run(PlayerDataPkt msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                switch (this.message) {
                    case "elona.player.status.async" -> {}
                    case "elona.player.status.client" -> {}
                }
            } else {
                switch (this.message) {
                    case "elona.player.status.open" -> {}
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
