package com.savagevegetables.outbreak;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.savagevegetables.outbreak.init.BlockInit;
import com.savagevegetables.outbreak.init.ItemInit;
import com.savagevegetables.outbreak.init.SoundInit;
import com.savagevegetables.outbreak.init.CreativeTabInit;
import com.savagevegetables.outbreak.init.EntityInit;
import com.savagevegetables.outbreak.init.StructureInit;
import com.savagevegetables.outbreak.init.StructurePieceInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import com.savagevegetables.outbreak.entity.mob.vegetables.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SavageVegetablesOutbreak.MODID)
public class SavageVegetablesOutbreak
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "savage_vegetables";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public SavageVegetablesOutbreak(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerAttributes);

        // Register the Deferred Registers
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);
        EntityInit.ENTITY_TYPES.register(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);
        StructureInit.STRUCTURE_TYPES.register(modEventBus);
        StructurePieceInit.STRUCTURE_PIECE_TYPES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.PUMPKIN_ENTITY.get(), PumpkinEntity.createAttributes().build());
        event.put(EntityInit.TOMATO_ENTITY.get(), TomatoEntity.createAttributes().build());
        event.put(EntityInit.CORN_ENTITY.get(), CornEntity.createAttributes().build());

        event.put(EntityInit.JERUSALEM_ARTICHOKE_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.boss.BossVegetableEntity.createBossAttributes().build());
        event.put(EntityInit.RUTABAGA_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.boss.BossVegetableEntity.createBossAttributes().build());
        event.put(EntityInit.CELERY_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.boss.BossVegetableEntity.createBossAttributes().build());

        event.put(EntityInit.HYBRID_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.stage2.HybridEntity.createAttributes().build());
        event.put(EntityInit.EGGPLANT_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.stage2.EggplantEntity.createAttributes().build());
        event.put(EntityInit.ZUCCHINI_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.stage2.ZucchiniEntity.createAttributes().build());
        event.put(EntityInit.LEEK_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.LeekEntity.createAttributes().build());
        event.put(EntityInit.CUCUMBER_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.CucumberEntity.createAttributes().build());
        event.put(EntityInit.POTATO_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.PotatoEntity.createAttributes().build());
        event.put(EntityInit.CARROT_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.CarrotEntity.createAttributes().build());
        event.put(EntityInit.BROCCOLI_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.BroccoliEntity.createAttributes().build());
        event.put(EntityInit.RADISH_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.RadishEntity.createAttributes().build());
        event.put(EntityInit.BELL_PEPPER_ENTITY.get(), com.savagevegetables.outbreak.entity.mob.vegetables.BellPepperEntity.createAttributes().build());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
