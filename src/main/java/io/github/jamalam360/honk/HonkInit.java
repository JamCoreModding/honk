/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Jamalam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.jamalam360.honk;

import io.github.jamalam360.honk.data.recipe.CentrifugeRecipe;
import io.github.jamalam360.honk.data.recipe.DnaCombinatorRecipe;
import io.github.jamalam360.honk.data.recipe.DnaInjectorExtractorRecipe;
import io.github.jamalam360.honk.data.type.HonkTypeResourceReloadListener;
import io.github.jamalam360.honk.registry.HonkBlocks;
import io.github.jamalam360.honk.registry.HonkCommands;
import io.github.jamalam360.honk.registry.HonkEntities;
import io.github.jamalam360.honk.registry.HonkItems;
import io.github.jamalam360.honk.registry.HonkScreens;
import io.github.jamalam360.honk.registry.HonkSounds;
import io.github.jamalam360.honk.registry.HonkWorldGen;
import io.github.jamalam360.jamlib.compatibility.JamLibCompatibilityModuleHandler;
import io.github.jamalam360.jamlib.log.JamLibLogger;
import io.github.jamalam360.jamlib.registry.JamLibRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

public class HonkInit implements ModInitializer {

    public static final String MOD_ID = "honk";
    public static final JamLibLogger LOGGER = JamLibLogger.getLogger(MOD_ID);
    public static final ItemGroup GROUP = FabricItemGroup.builder().icon(HonkItems.BLOOD_SYRINGE::getDefaultStack).name(Text.translatable("group.honk.main")).build();
    public static RegistryKey<ItemGroup> GROUP_KEY;

    public static Identifier idOf(String path) {
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize(ModContainer mod) {
        Registry.register(Registries.ITEM_GROUP, idOf("group"), GROUP);
        GROUP_KEY = Registries.ITEM_GROUP.getKey(GROUP).get();

        JamLibRegistry.register(HonkBlocks.class, HonkEntities.class, HonkItems.class, HonkScreens.class, HonkSounds.class);
        HonkWorldGen.init();
        HonkCommands.init();
        CentrifugeRecipe.init();
        DnaInjectorExtractorRecipe.init();
        DnaCombinatorRecipe.init();
        ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(HonkTypeResourceReloadListener.INSTANCE);
        JamLibCompatibilityModuleHandler.initialize(MOD_ID);
        LOGGER.logInitialize();
    }
}
