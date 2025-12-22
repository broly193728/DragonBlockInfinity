package com.dragonblockinfinity;

import com.dragonblockinfinity.player.capability.PlayerData;
import com.dragonblockinfinity.player.capability.PlayerDataProvider;
import com.dragonblockinfinity.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DragonBlockInfinity.MOD_ID)
public class DragonBlockInfinity {
    public static final String MOD_ID = "dragonblockinfinity";
    public static final Logger LOGGER = LogManager.getLogger();

    public DragonBlockInfinity() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        DBIBlocks.BLOCKS.register(modBus);
        DBIItems.ITEMS.register(modBus);
        DBICreativeTabs.CREATIVE_TABS.register(modBus);
        DBIMenuTypes.MENU_TYPES.register(modBus);

        modBus.addListener(this::registerCapabilities);

        LOGGER.info("DragonBlockInfinity initialized successfully!");
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerData.class);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(PlayerDataProvider.PLAYER_DATA).isPresent()) {
                    event.addCapability(new ResourceLocation(MOD_ID, "player_data"), 
                        new PlayerDataProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(oldData -> {
                    event.getEntity().getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(newData -> {
                        newData.copyFrom(oldData);
                    });
                });
            }
        }
    }
}
