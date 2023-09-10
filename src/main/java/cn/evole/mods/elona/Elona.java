package cn.evole.mods.elona;

import cn.evole.mods.elona.init.config.ModConfig;
import cn.evole.mods.elona.util.FileUtil;
import cn.evole.mods.elona.common.attribute.ModAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import static cn.evole.mods.elona.Static.ELONA_FOLDER;

@Mod(Static.MOD_ID)
public class Elona
{

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Static.MOD_ID);


//    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
//            .withTabsBefore(CreativeModeTabs.COMBAT)
//            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
//            .displayItems((parameters, output) -> {
//                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
//            }).build());

    public Elona()
    {
        FileUtil.checkFolder(ELONA_FOLDER.toPath());

        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);

        ModAttributes.register(modEventBus);
        ModConfig.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }


}
