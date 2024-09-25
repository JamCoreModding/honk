package io.github.jamalam360.honk;

import io.github.jamalam360.jamlib.JamLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HonkMod {
	public static final String MOD_ID = "honk";
	public static final String MOD_NAME = "Honk";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static void init() {
		JamLib.checkForJarRenaming(HonkMod.class);
	}
}
