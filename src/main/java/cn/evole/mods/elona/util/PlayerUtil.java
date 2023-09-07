package cn.evole.mods.elona.util;

import cn.evole.mods.elona.common.core.player.PlayerData;
import cn.evole.mods.elona.common.core.player.PlayerDataManager;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

/**
 * Name: Elona-forged / PlayerUtil
 * Author: cnlimiter
 * CreateTime: 2023/9/6 21:42
 * Description:
 */

public class PlayerUtil {
    private static final int HP_MAX_BASE = 120;
    private static final int HP_MAX_OFFSET = 20;

    public static void onClone(Player player, PlayerData data, boolean heal) {
        fixMaxHp(player, PlayerDataManager.getLv(data.getAttributes().getXp()), heal);
    }

    public static void onLvUp(Player player, PlayerData playerData) {
        final int lv = PlayerDataManager.getLv(playerData.getAttributes().getXp());
        if (lv - PlayerDataManager.getLv(playerData.getAttributes().getXpLast()) > 0) {
            fixMaxHp(player, lv, true);
        }
    }

    public static void fixMaxHp(Player player, final int lv, boolean heal) {
        //计算血量
        int maxHealth = HP_MAX_BASE + HP_MAX_OFFSET * lv;
        int maxHealthAdd = maxHealth - (int) player.getAttributeValue(Attributes.MAX_HEALTH);
        //执行
        AttributeUtil.modify(player, Attributes.MAX_HEALTH, maxHealthAdd, AttributeModifier.Operation.ADDITION, "Max Health");
        //fix回复
        if (heal) {
            player.heal(maxHealthAdd);
        }
    }
}
