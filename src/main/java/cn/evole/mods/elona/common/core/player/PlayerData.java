package cn.evole.mods.elona.common.core.player;

import cn.evole.mods.elona.ModConfig;
import cn.evole.mods.elona.common.net.pkt.FriendsDataPkt;
import cn.evole.mods.elona.common.net.pkt.PlayerDataPkt;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Elona-forged / PlayerData
 * Author: cnlimiter
 * CreateTime: 2023/9/6 14:50
 * Description:
 */
@Data
public class PlayerData implements Serializable {
    @Serial
    private static final long serialVersionUID = -3997902693840980675L;
    private final String playerUUID;
    //玩家主能力
    private PlayerAttributes attributes;
    //好友uuid列表
    private List<String> friendsUUID;

    public List<String> getFriendsUUID() {
        if (friendsUUID == null) {
            friendsUUID = new ArrayList<>();
        }
        return friendsUUID;
    }

    public PlayerData(String playerUUID) {
        this.playerUUID = playerUUID;
        this.attributes = new PlayerAttributes();
        this.friendsUUID = new ArrayList<>();
    }

    public PlayerData(PlayerDataPkt pack) {
        this.playerUUID = pack.getPlayerUUID();
        this.attributes = pack.getAttributes();
        this.friendsUUID = new ArrayList<>();
    }

    public PlayerData(FriendsDataPkt pack) {
        this.playerUUID = pack.getPlayerUUID();
        this.attributes = new PlayerAttributes();
        this.friendsUUID = pack.getFriendsUUID();
    }

    public void update(PlayerData data) {
        if (data != null && this.playerUUID.equals(data.playerUUID)) {
            if (data.getAttributes().getXpLast() > 0) {
                this.attributes.setXpLast(data.getAttributes().getXpLast());
            }
            if (data.getAttributes().getXp() > 0) {
                this.attributes.setXp(data.attributes.getXp());
            }
            if (data.getFriendsUUID() != null && !data.getFriendsUUID().isEmpty()) {
                this.friendsUUID = data.getFriendsUUID();
            }
        }

    }

    /**
     * 获取默认
     *
     * @param playerUUID 玩家UUID
     */
    private static PlayerData defaultData(String playerUUID) {
        return new PlayerData(playerUUID);
    }

    /**
     * 获得xp
     *
     * @param amount 值
     */
    public void addXp(int amount) {
        this.attributes.setXpLast(this.attributes.getXp());
        this.attributes.setXp(this.attributes.getXp() + amount);
        //限制最高等级
        int lvPoint = PlayerDataManager.getLvNextXpPoint(ModConfig.max_level - 1);
        if (lvPoint != 0 && this.attributes.getXp() > lvPoint) {
            this.attributes.setXp(lvPoint);
        }
    }

    //序列化
    public static void serialize(String playerDataPath, String playerUUID) {

    }

    //反序列化
    public static PlayerData deserialize(String playerDataPath, String playerUUID) {
        return new PlayerData("");
    }

}
