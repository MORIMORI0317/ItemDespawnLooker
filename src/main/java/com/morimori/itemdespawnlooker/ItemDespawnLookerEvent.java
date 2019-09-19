package com.morimori.itemdespawnlooker;

import com.mojang.blaze3d.platform.GlStateManager;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemDespawnLookerEvent {

	public static boolean isDespawnTime = ItemDespawnLookerConfig.isDespawnTime;
	public static boolean isItemName = ItemDespawnLookerConfig.isItemName;

	public static boolean isViewHeightInclination = ItemDespawnLookerConfig.isViewHeightInclination;

	public static int DespawnTimetyape = ItemDespawnLookerConfig.DespawnTimetyape;
	public static boolean isTimeOnry = ItemDespawnLookerConfig.isTimeOnry;

	public static boolean isDetailedItemName = ItemDespawnLookerConfig.isDetailedItemName;

	public void onRenderWorldLastEvent(RenderWorldLastEvent e) {

		ClientWorld client = Minecraft.getInstance().world;
		Int2ObjectMap<Entity> enID = ObfuscationReflectionHelper.getPrivateValue(ClientWorld.class, client,
				"field_217429_b");
		for (Entity entity : enID.values()) {

			if (entity instanceof ItemEntity) {

				ItemEntity ie = (ItemEntity) entity;
				String desTimeS = null;
				int itemI = 0;

				if (isDespawnTime) {
					int despawntick = 6000 - ie.getAge();

					if (DespawnTimetyape == 0) {
						desTimeS = I18n.format("looker.itemdespawnlooker.remaining_tick", despawntick);
						if (isTimeOnry) {
							desTimeS = I18n.format("looker.itemdespawnlooker.tick", despawntick);

						}
					}
					if (DespawnTimetyape == 1) {
						desTimeS = I18n.format("looker.itemdespawnlooker.remaining_second", despawntick / 20);
						if (isTimeOnry) {
							desTimeS = I18n.format("looker.itemdespawnlooker.second", despawntick / 20);

						}
					}
					if (DespawnTimetyape == 2) {
						desTimeS = I18n.format("looker.itemdespawnlooker.remaining_minute", despawntick / 20 / 60);
						if (isTimeOnry) {
							desTimeS = I18n.format("looker.itemdespawnlooker.minute", despawntick / 20 / 60);

						}
					}

				}
				if (desTimeS != null) {
					renderItemString(ie, desTimeS, 0, 00000);
					itemI = 1;
				}

				if (isItemName) {

					int SitemI = itemI;
					if (isDetailedItemName) {

						if (ie.getItem().isDamageable()) {
							renderItemString(ie,
									I18n.format("item.durability",
											ie.getItem().getMaxDamage() - ie.getItem().getDamage(),
											ie.getItem().getMaxDamage()),
									SitemI, 16777215);
							SitemI++;
						}

					}

					renderItemString(ie, ie.getDisplayName().getString(), SitemI,
							ie.getItem().getRarity().color.getColor());

				}

			}
		}

	}

	public void renderItemString(ItemEntity e, String a, int i, int day) {

		double RX = ObfuscationReflectionHelper.getPrivateValue(EntityRendererManager.class,
				Minecraft.getInstance().getRenderManager(),
				"field_78725_b");
		double RY = ObfuscationReflectionHelper.getPrivateValue(EntityRendererManager.class,
				Minecraft.getInstance().getRenderManager(),
				"field_78726_c");
		double RZ = ObfuscationReflectionHelper.getPrivateValue(EntityRendererManager.class,
				Minecraft.getInstance().getRenderManager(),
				"field_78723_d");

		float in = 0.2f * i;

		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) (e.lastTickPosX - RX), (float) (e.lastTickPosY - RY + 3 + in) - 2,
				(float) (e.lastTickPosZ - RZ));
		GlStateManager.scalef(-0.02f, -0.02f, 0.02f);
		GlStateManager.rotatef(Minecraft.getInstance().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);

		if (isViewHeightInclination) {
			GlStateManager.rotatef(-Minecraft.getInstance().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
		}
		String t = a;
		Minecraft.getInstance().fontRenderer.drawString(t,
				-Minecraft.getInstance().fontRenderer.getStringWidth(t) * 0.5f, 0, day);

		GlStateManager.popMatrix();

	}
}