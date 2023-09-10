package cn.evole.mods.elona.common.core.player;

import cn.evole.mods.elona.init.config.ModConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: Elona-forged / PlayerDataManager
 * Author: cnlimiter
 * CreateTime: 2023/9/6 20:25
 * Description:
 */

public class PlayerDataManager {
    private static final Map<String, PlayerData> MAP = new HashMap<>(8);

    public static PlayerData get(String playerUUID) {
        return MAP.get(playerUUID);
    }

    public static PlayerData put(PlayerData data) {
        return MAP.put(data.getPlayerUUID(), data);
    }

    public static void update(PlayerData data) {
        if (MAP.containsKey(data.getPlayerUUID())) {
            PlayerData playerData = MAP.get(data.getPlayerUUID());
            playerData.update(data);
        } else {
            MAP.put(data.getPlayerUUID(), data);
        }
    }

    public static final int LV_NEED_XP_BASE = 202191;
    public static final int LV_NEED_XP_OFFSET = 1126;
    public static final List<Integer> LV_NEED_XP;

    static {
        LV_NEED_XP = new ArrayList<>();
        LV_NEED_XP.add(LV_NEED_XP_BASE / LV_NEED_XP_OFFSET);
        for (int i = 1; i <= ModConfig.max_level; i++) {
            LV_NEED_XP.add(LV_NEED_XP.get(i - 1) + LV_NEED_XP_BASE * i / LV_NEED_XP_OFFSET);
        }
    }

    /**
     * 获取当前等级
     *
     * @param xp 当前经验值
     */
    public static int getLv(int xp) {
        int lv = 0;
        for (int i = 0; i < LV_NEED_XP.size(); i++) {
            if (xp >= LV_NEED_XP.get(i)) {
                lv = i;
            } else {
                break;
            }
        }
        return lv;
    }

    /**
     * 获取升级所需要达到的经验值点
     *
     * @param lv 当前等级
     */
    public static int getLvNextXpPoint(int lv) {
        if (ModConfig.max_level > lv && lv > -1) {
            return LV_NEED_XP.get(lv + 1);
        } else {
            return 0;
        }
    }
}
