package cn.evole.mods.elona.common.core.friend;

import lombok.Getter;
import net.minecraft.server.level.ServerPlayer;

/**
 * Name: Elona-forged / Friend
 * Author: cnlimiter
 * CreateTime: 2023/9/6 20:17
 * Description:
 */

@Getter
public class Friend {
    private String uuid;
    private String name;

    public Friend(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Friend of(ServerPlayer player) {
        return new Friend(player.getStringUUID(), player.getDisplayName().getString());
    }
}
