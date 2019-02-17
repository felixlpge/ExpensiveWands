package de.felixlpge.expensivewands.blocks

import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity

class TileEntityBlockPress extends TileEntity{

  private var items: List[ItemStack]= List[ItemStack]()

  def getItemsInside(): Array[ItemStack] = {
    items.toArray
  }

  def clear(): Unit = {
    items = List[ItemStack]()
  }

  def addItem(item: ItemStack): Unit ={
    items ::= item
  }

}
