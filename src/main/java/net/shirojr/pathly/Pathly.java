package net.shirojr.pathly;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.shirojr.pathly.init.PathlyGamerules;
import net.shirojr.pathly.init.PathlyTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pathly implements ModInitializer {
	public static final String MOD_ID = "pathly";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PathlyGamerules.initialize();
		PathlyTags.initialize();

		LOGGER.info("There are no wrong turns, only unexpected paths - Mark Nepo");
	}

	public static Identifier getId(String path) {
		return Identifier.of(MOD_ID, path);
	}
}