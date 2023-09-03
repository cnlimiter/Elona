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
    public static final RegistryObject<Attribute> CONSTITUTION = ATTRIBUTES.register("elona.constitution", () -> new BaseAttribute("attribute.name.elona.constitution", 20.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> DEXTERITY = ATTRIBUTES.register("elona.dexterity", () -> new BaseAttribute("attribute.name.elona.dexterity", 20.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> PERCEPTION = ATTRIBUTES.register("elona.perception", () -> new BaseAttribute("attribute.name.elona.perception", 20.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> LEARNING = ATTRIBUTES.register("elona.learning", () -> new BaseAttribute("attribute.name.elona.learning", 20.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> WILL = ATTRIBUTES.register("elona.will", () -> new BaseAttribute("attribute.name.elona.will", 20.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> MAGIC = ATTRIBUTES.register("elona.magic", () -> new BaseAttribute("attribute.name.elona.magic", 20.0D, 1.0D, 1024.0D));
    public static final RegistryObject<Attribute> CHARISMA = ATTRIBUTES.register("elona.charisma", () -> new BaseAttribute("attribute.name.elona.charisma", 20.0D, 1.0D, 1024.0D));



    public static void register(IEventBus event){
        ATTRIBUTES.register(event);
    }

}
