package de.felixlpge.expensivewands.crafting

import java.util

import net.minecraft.item.Item

class WandCraftingRecipe(out: Item,in: Array[Item], tier: java.lang.Integer, powerUse: java.lang.Integer) {
  def getOutput : Item = out
  def getInput : Array[Item] = in.clone()
  def getNeededTier: java.lang.Integer = tier
  def getPowerUse: java.lang.Integer = powerUse

  def doItemsMatch(items: Array[Item]) : Boolean = {
    var needed = util.Arrays.asList(getInput)
    for (item <- items){
      if (needed.contains(item)){
        needed.remove(item)
      }else{
        return false
      }
    }
    if (needed.size() > 0){
      return false
    }
    true
  }

  def doTierMatch(tier: Int): Unit = tier >= getNeededTier

}
