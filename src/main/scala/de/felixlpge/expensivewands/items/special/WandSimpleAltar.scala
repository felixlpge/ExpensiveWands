package de.felixlpge.expensivewands.items.special

import de.felixlpge.expensivewands.items.WandBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.{Blocks, Items}
import net.minecraft.inventory.IInventory
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumActionResult, EnumFacing, EnumHand}
import net.minecraft.world.World

class WandSimpleAltar extends WandBase(20000){

  override def onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    if (!worldIn.isRemote && !player.getHeldItem(hand).isEmpty && hasEnergy(player.getHeldItem(hand), 10000)){
      if (worldIn.getBlockState(pos).getBlock == Blocks.CHEST && worldIn.getTileEntity(pos) != null){
        var chest: TileEntityChest = worldIn.getTileEntity(pos).asInstanceOf[TileEntityChest]
        if (chest.getStackInSlot(0).getItem == Items.STICK){
          //TODO implement systme
        }
      }
    }
    EnumActionResult.SUCCESS
  }

}
