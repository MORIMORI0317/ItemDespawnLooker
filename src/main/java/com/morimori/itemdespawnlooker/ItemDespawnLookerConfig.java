package com.morimori.itemdespawnlooker;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ItemDespawnLookerConfig {
	public static boolean isDespawnTime;
	public static boolean isItemName;
	public static boolean isViewHeightInclination;
	public static int DespawnTimetyape;
	public static boolean isTimeOnry;
	public static boolean isDetailedItemName;

	private static ConfigValue<Boolean> isDespawnTime_config;
	private static ConfigValue<Boolean> isItemName_config;
	private static ConfigValue<Boolean> isViewHeightInclination_config;
	private static ConfigValue<Boolean> isTimeOnry_config;
	private static ConfigValue<Boolean> isDetailedItemName_config;
	private static ConfigValue<Integer> DespawnTimetyape_config;

	public static void init() {
		Pair<ConfigLoder, ForgeConfigSpec> config = new ForgeConfigSpec.Builder().configure(ConfigLoder::new);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, config.getRight());

	}

	public static void init2() {
		isDespawnTime = isDespawnTime_config.get();
		isItemName = isItemName_config.get();
		isViewHeightInclination = isViewHeightInclination_config.get();
		isTimeOnry = isTimeOnry_config.get();
		isDetailedItemName = isDetailedItemName_config.get();
		DespawnTimetyape = DespawnTimetyape_config.get();

	}

	static class ConfigLoder {

		public ConfigLoder(ForgeConfigSpec.Builder builder) {
			builder.push("general");

			isDespawnTime_config = builder.define("Whether to display Despawn time", true);
			isItemName_config = builder.define("Whether to display Item name", true);
			isViewHeightInclination_config = builder.define("lean text", true);
			isTimeOnry_config = builder.define("Whether to display time onry", false);
			isDetailedItemName_config = builder.define("Whether to display endurance", false);
			DespawnTimetyape_config = builder.define("How to express time--0,tick--1,second--2,minute", 1);

			builder.pop();

		}

	}
}
