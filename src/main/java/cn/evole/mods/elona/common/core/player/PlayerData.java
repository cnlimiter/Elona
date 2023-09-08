package cn.evole.mods.elona.common.core.player;

import cn.evole.mods.elona.ModConfig;
import cn.evole.mods.elona.Static;
import cn.evole.mods.elona.common.net.pkt.FriendsDataPkt;
import cn.evole.mods.elona.common.net.pkt.PlayerDataPkt;
import lombok.Data;
import org.noear.wood.DbContext;

import java.io.*;
import java.sql.SQLException;
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
    private MainAttributes attributes;
    //玩家状态
    private MainStats stats;
    //玩家信息
    private MainAdditions additions;
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
        this.attributes = new MainAttributes();
        this.stats = new MainStats();
        this.additions = new MainAdditions();
        this.friendsUUID = new ArrayList<>();
    }

    //序列化用
    public PlayerData(String playerUUID,
                      int strength, int constitution, int dexterity, int perception, int learning, int will, int magic, int charisma,
                      int age, int height, int weight, int life, int mana, int mpLast, int mp, int xpLast, int xp,
                      int gender, int role, int origin, int title, int father, int mother, int faith, int guild,
                      List<String> friendsUUID
                      ) {
        this.playerUUID = playerUUID;
        this.attributes = new MainAttributes(strength, constitution, dexterity, perception, learning, will, magic, charisma);
        this.stats = new MainStats(age, height,weight, life, mana, mpLast, mp, xpLast, xp);
        this.additions = new MainAdditions(gender, role, origin, title ,father, mother, faith, guild);
        this.friendsUUID = friendsUUID;
    }

    public PlayerData(PlayerDataPkt pack) {
        this.playerUUID = pack.getPlayerUUID();
        this.friendsUUID = new ArrayList<>();
        this.attributes = pack.getAttributes();
        this.stats = new MainStats();
        this.additions = new MainAdditions();
    }

    public PlayerData(FriendsDataPkt pack) {
        this.playerUUID = pack.getPlayerUUID();
        this.friendsUUID = pack.getFriendsUUID();
        this.attributes = new MainAttributes();
        this.stats = new MainStats();
        this.additions = new MainAdditions();
    }

    public void update(PlayerData data) {
        if (data != null && this.playerUUID.equals(data.playerUUID)) {
            if (data.getStats().getXpLast() > 0) {
                this.stats.setXpLast(data.getStats().getXpLast());
            }
            if (data.getStats().getXp() > 0) {
                this.stats.setXp(data.stats.getXp());
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
        this.stats.setXpLast(this.stats.getXp());
        this.stats.setXp(this.stats.getXp() + amount);
        //限制最高等级
        int lvPoint = PlayerDataManager.getLvNextXpPoint(ModConfig.max_level - 1);
        if (lvPoint != 0 && this.stats.getXp() > lvPoint) {
            this.stats.setXp(lvPoint);
        }
    }

    //序列化
    public static void serialize(DbContext db, String playerUUID){
        try {
            //从玩家数据管理获取
            PlayerData data = PlayerDataManager.get(playerUUID);
            if (data != null) {
                db.table("player_data")
                        .set("uuid", playerUUID)

                        //attributes
                        .set("strength", data.attributes.getStrength())
                        .set("constitution", data.attributes.getConstitution())
                        .set("dexterity", data.attributes.getDexterity())
                        .set("perception", data.attributes.getPerception())
                        .set("learning", data.attributes.getLearning())
                        .set("will", data.attributes.getWill())
                        .set("magic", data.attributes.getMagic())
                        .set("charisma", data.attributes.getCharisma())

                        //stats
                        .set("age", data.stats.getAge())
                        .set("height", data.stats.getHeight())
                        .set("weight", data.stats.getWeight())
                        .set("life", data.stats.getLife())
                        .set("mana", data.stats.getMana())
                        .set("mpLast", data.stats.getMpLast())
                        .set("mp", data.stats.getMp())
                        .set("xpLast", data.stats.getXpLast())
                        .set("xp", data.stats.getXp())

                        //additions
                        .set("gender", data.additions.getGender())
                        .set("role", data.additions.getRole())
                        .set("origin", data.additions.getOrigin())
                        .set("title", data.additions.getTitle())
                        .set("father", data.additions.getFather())
                        .set("mother", data.additions.getMother())
                        .set("faith", data.additions.getFaith())
                        .set("guild", data.additions.getGuild())

                        .updateBy("uuid");//更新+新建
            }
        } catch (Exception e) {
            Static.LOGGER.error("PlayerData 序列化异常：" + playerUUID);
        }

    }

    //反序列化
    public static PlayerData deserialize(DbContext db, String playerUUID){
        //从玩家数据管理获取
        PlayerData data = PlayerDataManager.get(playerUUID);
        if (data != null) {
            return data;
        }
        try {
            data = db.table("player_data").where("uuid=?", playerUUID)
                    .selectItem("*", PlayerData.class);
        } catch (Exception e) {
            Static.LOGGER.error("PlayerData 反序列化异常：" + playerUUID, e);
        }
        if (data == null) {
            data = defaultData(playerUUID);
        }
        //加入玩家数据管理
        PlayerDataManager.put(data);
        return data;
    }

}
