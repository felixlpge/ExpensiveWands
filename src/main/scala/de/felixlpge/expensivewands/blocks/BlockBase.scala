package de.felixlpge.expensivewands.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.ItemBlock

class BlockBase(material: Material) extends Block(material) {
  def createItemBlock: ItemBlock = {
    var item = new ItemBlock(this)
    item.setRegistryName(getRegistryName)
    item
  }
}
