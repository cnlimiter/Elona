package cn.evole.mods.elona.util;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

/**
 * Name: Elona-forged / AttributeUtil
 * Author: cnlimiter
 * CreateTime: 2023/9/6 21:35
 * Description:
 */

public class AttributeUtil {

    public static void modify(Player player, Attribute attribute, final int value, AttributeModifier.Operation operation, String modifyName) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(attribute, new AttributeModifier(modifyName + "Modify", value, operation));
        player.getAttributes().addTransientAttributeModifiers(builder.build());
    }
}
