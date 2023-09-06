package cn.evole.mods.elona.common.net.pkt;

import cn.evole.mods.elona.api.common.net.IPacket;
import cn.evole.mods.elona.common.core.friend.Friend;
import cn.evole.mods.elona.common.core.player.PlayerData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Name: Elona-forged / FriendsDataPkt
 * Author: cnlimiter
 * CreateTime: 2023/9/6 20:16
 * Description:
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FriendsDataPkt extends IPacket<FriendsDataPkt> {
    private final static Gson GSON = new Gson();

    private final String message;
    private final String playerUUID;
    private final String targetUUID;
    private final List<String> friendsUUID;
    private final List<Friend> onlinePlayer;

    public FriendsDataPkt(String message) {
        this.message = message;
        this.playerUUID = "null";
        this.targetUUID = "null";
        this.friendsUUID = new ArrayList<>();
        this.onlinePlayer = new ArrayList<>();
    }

    public FriendsDataPkt(String message, String targetUUID) {
        this.message = message;
        this.playerUUID = "null";
        this.targetUUID = targetUUID;
        this.friendsUUID = new ArrayList<>();
        this.onlinePlayer = new ArrayList<>();
    }

    public FriendsDataPkt(String message, PlayerData data, List<Friend> onlinePlayer) {
        this.message = message;
        this.playerUUID = data.getPlayerUUID();
        this.targetUUID = "null";
        this.friendsUUID = data.getFriendsUUID();
        this.onlinePlayer = onlinePlayer;
    }


    public FriendsDataPkt read(FriendlyByteBuf buf) {
        var message = buf.readUtf(Short.MAX_VALUE);
        var playerUUID = buf.readUtf(Short.MAX_VALUE);
        var targetUUID = buf.readUtf(Short.MAX_VALUE);
        List<String> friendsUUID = GSON.fromJson(buf.readUtf(Short.MAX_VALUE), new TypeToken<ArrayList<String>>() {
        }.getType());
        List<Friend> onlinePlayer = GSON.fromJson(buf.readUtf(Short.MAX_VALUE), new TypeToken<ArrayList<Friend>>() {
        }.getType());
        return new FriendsDataPkt(message, playerUUID, targetUUID, friendsUUID, onlinePlayer);
    }

    @Override
    public void write(FriendsDataPkt msg, FriendlyByteBuf buf) {
        buf.writeUtf(this.message);
        buf.writeUtf(this.playerUUID);
        buf.writeUtf(this.targetUUID);
        buf.writeUtf(GSON.toJson(this.friendsUUID));
        buf.writeUtf(GSON.toJson(this.onlinePlayer));
    }

    @Override
    public void run(FriendsDataPkt msg, Supplier<NetworkEvent.Context> ctx) {
        //todo
    }
}
