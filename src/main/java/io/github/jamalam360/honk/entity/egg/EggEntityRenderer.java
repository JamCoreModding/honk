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

package io.github.jamalam360.honk.entity.egg;

import io.github.jamalam360.honk.HonkClientInit;
import io.github.jamalam360.honk.HonkInit;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;

public class EggEntityRenderer extends MobEntityRenderer<EggEntity, EggEntityModel> {

	public EggEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new EggEntityModel(context.getPart(HonkClientInit.EGG_LAYER)), 0.25f);
	}

	@Override
	public Identifier getTexture(EggEntity entity) {
		return HonkInit.idOf("textures/entity/egg.png");
	}

	@Override
	public void render(EggEntity egg, float f, float g, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrices.multiply(Axis.X_NEGATIVE.rotationDegrees(egg.wobbleAngle));
		super.render(egg, f, g, matrices, vertexConsumerProvider, i);
	}
}
