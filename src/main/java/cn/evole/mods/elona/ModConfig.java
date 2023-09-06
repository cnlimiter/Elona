package cn.evole.mods.elona;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Static.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfig
{
    private static final ForgeConfigSpec.Builder COMMON = new ForgeConfigSpec.Builder();

    static final ForgeConfigSpec SPEC = COMMON.build();

    public static int max_level;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

        COMMON.comment("Avaritia Base Config").push("general");
        max_level = buildInt(COMMON, "Max Level", 39, 0, 1000, "");
        COMMON.pop();
    }


    public static void register() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, SPEC);
    }


    private static Boolean buildBoolean(ForgeConfigSpec.Builder builder, String name, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue).get();
    }

    private static Integer buildInt(ForgeConfigSpec.Builder builder, String name, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max).get();
    }

    private static Double buildDouble(ForgeConfigSpec.Builder builder, String name, double defaultValue, double min, double max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max).get();
    }
}
