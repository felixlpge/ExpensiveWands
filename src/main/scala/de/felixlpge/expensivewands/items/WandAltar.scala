package de.felixlpge.expensivewands.items

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumActionResult, EnumFacing, EnumHand}
import net.minecraft.world.World

class WandAltar(capacity: java.lang.Integer, tier: java.lang.Integer, name: java.lang.String) extends WandBase(capacity){

  override def onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    if (!worldIn.isRemote && !player.getHeldItem(hand).isEmpty){
      if (worldIn.getBlockState(pos).getBlock == Blocks.CHEST && worldIn.getTileEntity(pos) != null){
        var chest: TileEntityChest = worldIn.getTileEntity(pos).asInstanceOf[TileEntityChest]

      }
    }
    EnumActionResult.SUCCESS
  }

}
