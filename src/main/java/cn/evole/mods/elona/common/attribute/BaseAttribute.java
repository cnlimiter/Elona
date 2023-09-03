package cn.evole.mods.elona.common.attribute;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;

/**
 * Name: Elona / BaseAttribute
 * Author: cnlimiter
 * CreateTime: 2023/9/3 4:59
 * Description:
 */

public class BaseAttribute extends RangedAttribute {
    public BaseAttribute(String descriptionId, double defaultValue, double minValue, double maxValue) {
        super(descriptionId, defaultValue, minValue, maxValue);
        this.setSyncable(true);
    }
}
