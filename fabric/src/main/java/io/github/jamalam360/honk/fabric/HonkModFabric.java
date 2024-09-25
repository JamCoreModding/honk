package io.github.jamalam360.honk.fabric;

import io.github.jamalam360.honk.HonkMod;
import net.fabricmc.api.ModInitializer;

public class HonkModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        HonkMod.init();
    }
}
