package de.felixlpge.expensivewands.blocks

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TileEntityBlockPress extends TileEntity {

  private var items: List[ItemStack] = List[ItemStack]()

  def getItemsInside(): Array[ItemStack] = {
    items.toArray
  }

  def clear(): Unit = {
    items = List[ItemStack]()
  }

  def addItem(item: ItemStack): Unit = {
    items ::= item
  }

  override def shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newSate: IBlockState): Boolean = {
    super.shouldRefresh(world, pos, oldState, newSate)
    false
  }

}
