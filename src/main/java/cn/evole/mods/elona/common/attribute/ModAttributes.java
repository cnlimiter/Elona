package cn.evole.mods.elona.common.attribute;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cn.evole.mods.elona.Static.MOD_ID;

/**
 * Name: Elona / ModAttributes
 * Author: cnlimiter
 * CreateTime: 2023/9/3 5:02
 * Description:
 */

public class ModAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MOD_ID);


    public static final RegistryObject<Attribute> STRENGTH = ATTRIBUTES.register("elona.strength", () -> new BaseAttribute("attribute.name.elona.strength", 20.0D, 1.0D, 1024.0D));


    public static void register(IEventBus event){
        ATTRIBUTES.register(event);
    }

}
