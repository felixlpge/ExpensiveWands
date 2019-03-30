package de.felixlpge.expensivewands

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack

class ExpensiveWandsCreativeTab extends CreativeTabs(expensivewands.MODID){
  override def getTabIconItem: ItemStack = new ItemStack(RegistrationHandler.wandSolar)
}
