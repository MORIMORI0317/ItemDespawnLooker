
package com.morimori.itemdespawnlooker;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ItemDespawnLooker.MODID)

public class ItemDespawnLooker {
	public static final String MODID = "itemdespawnlooker";
	public static final String MODNAME = "Item Despawn Looker";
	public static final String MODVER = "1.0.0";

	public ItemDespawnLooker() {

		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		ItemDespawnLookerConfig.init();

	}

	public void setup(FMLClientSetupEvent event) {
		ItemDespawnLookerConfig.init2();

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, false, RenderWorldLastEvent.class,
				new ItemDespawnLookerEvent()::onRenderWorldLastEvent);
	}
}