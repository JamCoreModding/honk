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

package io.github.jamalam360.honk.registry;

import io.github.jamalam360.honk.HonkInit;
import io.github.jamalam360.honk.block.centrifuge.CentrifugeScreenHandler;
import io.github.jamalam360.honk.block.dna_combinator.DnaCombinatorScreenHandler;
import io.github.jamalam360.honk.block.dna_injector_extractor.DnaInjectorExtractorScreenHandler;
import io.github.jamalam360.jamlib.registry.annotation.ContentRegistry;
import net.minecraft.feature_flags.FeatureFlagBitSet;
import net.minecraft.screen.ScreenHandlerType;

@SuppressWarnings("Convert2MethodRef")
@ContentRegistry(HonkInit.MOD_ID)
public class HonkScreens {

	public static final ScreenHandlerType<CentrifugeScreenHandler> CENTRIFUGE = new ScreenHandlerType<>((syncId, inventory) -> new CentrifugeScreenHandler(syncId, inventory), FeatureFlagBitSet.empty());
	public static final ScreenHandlerType<DnaInjectorExtractorScreenHandler> DNA_INJECTOR_EXTRACTOR = new ScreenHandlerType<>((syncId, inventory) -> new DnaInjectorExtractorScreenHandler(syncId, inventory), FeatureFlagBitSet.empty());
	public static final ScreenHandlerType<DnaCombinatorScreenHandler> DNA_COMBINATOR = new ScreenHandlerType<>((syncId, inventory) -> new DnaCombinatorScreenHandler(syncId, inventory), FeatureFlagBitSet.empty());
}
