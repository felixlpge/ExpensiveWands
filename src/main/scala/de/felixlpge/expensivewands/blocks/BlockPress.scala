package de.felixlpge.expensivewands.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockPress extends BlockBase(Material.ANVIL){
  setRegistryName("press")
  setUnlocalizedName("press")

  override def createTileEntity(world: World, state: IBlockState): TileEntity = new TileEntityBlockPress

  override def onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    if (worldIn.getTileEntity(pos) != null){
      var press = worldIn.getTileEntity(pos).asInstanceOf[TileEntityBlockPress]
      press.addItem(playerIn.getHeldItem(hand))
      playerIn.setHeldItem(hand, null)
      return true
    }
    false
  }
}
