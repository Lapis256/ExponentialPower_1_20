package io.github.mosadie.ExponentialPower.blocks;

import io.github.mosadie.ExponentialPower.TileEntitys.BaseClasses.StorageTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EnderStorage extends Block {

	public EnderStorage() {
		super(BlockManager.BLOCK_PROPERTIES);

	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new StorageTE(StorageTE.StorageTier.REGULAR);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			StorageTE te = (StorageTE) worldIn.getTileEntity(pos);
			double percent = ((int)(te.energy/te.getMaxEnergy() * 10000.00)) / 100.00;
			player.sendStatusMessage(new StringTextComponent("Current Energy Stored: " + te.energy + " / " + te.getMaxEnergy() + " RF (" + percent + "%)"), true);
		}
		return ActionResultType.SUCCESS;
	}
}
