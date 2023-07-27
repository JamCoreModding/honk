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

import io.github.jamalam360.honk.block.centrifuge.CentrifugeScreen;
import io.github.jamalam360.honk.block.dna_combinator.DnaCombinatorScreen;
import io.github.jamalam360.honk.block.dna_injector_extractor.DnaInjectorExtractorScreen;
import io.github.jamalam360.honk.entity.egg.EggEntityModel;
import io.github.jamalam360.honk.entity.egg.EggEntityRenderer;
import io.github.jamalam360.honk.entity.honk.HonkEntityModel;
import io.github.jamalam360.honk.entity.honk.HonkEntityRenderer;
import io.github.jamalam360.honk.registry.HonkC2SNetwork;
import io.github.jamalam360.honk.registry.HonkEntities;
import io.github.jamalam360.honk.registry.HonkScreens;
import io.github.jamalam360.jamlib.keybind.JamLibKeybinds;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class HonkClientInit implements ClientModInitializer {

	public static final EntityModelLayer EGG_LAYER = new EntityModelLayer(HonkInit.idOf("egg"), "main");
	public static final EntityModelLayer HONK_LAYER = new EntityModelLayer(HonkInit.idOf("honk"), "main");

	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityRendererRegistry.register(HonkEntities.EGG, EggEntityRenderer::new);
		EntityRendererRegistry.register(HonkEntities.HONK, HonkEntityRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(EGG_LAYER, EggEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(HONK_LAYER, HonkEntityModel::getTexturedModelData);

		HandledScreens.register(HonkScreens.CENTRIFUGE, CentrifugeScreen::new);
		HandledScreens.register(HonkScreens.DNA_INJECTOR_EXTRACTOR, DnaInjectorExtractorScreen::new);
		HandledScreens.register(HonkScreens.DNA_COMBINATOR, DnaCombinatorScreen::new);

		JamLibKeybinds.register(new JamLibKeybinds.JamLibKeybind(HonkInit.MOD_ID, "honk", GLFW.GLFW_KEY_SLASH, (client) -> HonkC2SNetwork.HONK.send()));
	}
}
