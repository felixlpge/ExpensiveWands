package de.felixlpge.expensivewands.crafting

import de.felixlpge.expensivewands.RegistrationHandler
import net.minecraft.item.{Item, ItemStack}

class WandCraftingRecipe(out: Item, in: Array[Item], tier: Int, powerUse: java.lang.Integer) {
  def getOutput: Item = out

  def getInput: Array[Item] = in.clone()

  def getNeededTier: java.lang.Integer = tier

  def getPowerUse: java.lang.Integer = powerUse

  def doItemsMatch(items: Array[Item]): java.lang.Boolean = {
    var needed: List[Item] = getInput.toList
    for (item <- items) {
      if (needed.contains(item)) {
        needed = needed.filter(_ != item)
      } else {
        return false
      }
    }
    if (needed.nonEmpty) {
      return false
    }
    true
  }

  def doTierMatch(tier: Int): java.lang.Boolean = tier >= getNeededTier

  def getNeededWand: String = {
    tier match {
      case 0 => return "item.wand_crafting_I.name"
      case 1 => return "item.wand_crafting_II.name"
      case 2 => return "item.wand_crafting_III.name"
      case 3 => return "item.wand_crafting_IV.name"
      case 4 => return "item.wand_crafting_V.name"
      case _ => return "item.wand_crafting_V.name"
    }
    ""
  }

  def getItemWand: ItemStack = {
    var stack: ItemStack = new ItemStack(RegistrationHandler.wandCraftingV)
    tier match {
      case 0 => stack = new ItemStack(RegistrationHandler.wandCraftingI)
      case 1 => stack = new ItemStack(RegistrationHandler.wandCraftingII)
      case 2 => stack = new ItemStack(RegistrationHandler.wandCraftingIII)
      case 3 => stack = new ItemStack(RegistrationHandler.wandCraftingIV)
      case 4 => stack = new ItemStack(RegistrationHandler.wandCraftingV)
    }
    RegistrationHandler.wandCraftingV.receiveEnergy(stack, getPowerUse, simulate = false)
    stack
  }

}
