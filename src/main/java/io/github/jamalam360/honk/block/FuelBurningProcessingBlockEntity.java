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

package io.github.jamalam360.honk.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.item.content.registry.api.ItemContentRegistries;

import java.util.Optional;

public abstract class FuelBurningProcessingBlockEntity extends AbstractProcessingBlockEntity {

	private final int fuelSlot;
	private int burnTime = 0;
	private int maxBurnTime = 0;
	private boolean lastSyncedBurning = false;

	public FuelBurningProcessingBlockEntity(BlockEntityType<?> type, RecipeType<? extends Recipe<Inventory>> recipeType, int inventorySize, int fuelSlot, BlockPos pos, BlockState state) {
		super(type, recipeType, inventorySize, pos, state);
		this.fuelSlot = fuelSlot;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.lastSyncedBurning != this.burnTime > 0) {
			this.updateBlockState();
			this.lastSyncedBurning = this.burnTime > 0;
		}

		if (this.burnTime > 0) {
			this.burnTime--;

			if (!this.world.isClient && this.burnTime == 0 && this.getCurrentRecipe() != null) {
				this.tryBurnItemOrCancelRecipe();
				this.updateBlockState();
			}
		}

		this.markDirty();
	}

	public void updateBlockState() {
		if (this.burnTime == 0) {
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(FuelBurningProcessingBlock.LIT, false));
		} else {
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(FuelBurningProcessingBlock.LIT, true));
		}
	}

	public int getBurnTime() {
		return this.burnTime;
	}

	public int getMaxBurnTime() {
		return this.maxBurnTime;
	}

	@Override
	public boolean isPowered() {
		return ItemContentRegistries.FUEL_TIMES.get(this.getStack(fuelSlot).getItem()).orElse(0) > 0 || this.burnTime > 0;
	}

	@Override
	public void onBeginProcessing() {
		if (this.burnTime == 0) {
			this.tryBurnItemOrCancelRecipe();
		} else {
			super.onBeginProcessing();
		}

		this.updateBlockState();
	}

	public void tryBurnItemOrCancelRecipe() {
		Optional<Integer> fuelTime = ItemContentRegistries.FUEL_TIMES.get(this.getStack(fuelSlot).getItem());

		if (fuelTime.isPresent()) {
			this.getStack(fuelSlot).decrement(1);
			this.burnTime = fuelTime.get();
			this.maxBurnTime = fuelTime.get();
		}

		if (this.burnTime == 0) {
			this.cancelCurrentRecipe();
		}

		this.markDirty();
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		this.burnTime = nbt.getInt("BurnTime");
		this.lastSyncedBurning = this.burnTime == 0;
		this.maxBurnTime = nbt.getInt("MaxBurnTime");
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		nbt.putInt("BurnTime", this.burnTime);
		nbt.putInt("MaxBurnTime", this.maxBurnTime);
	}
}
