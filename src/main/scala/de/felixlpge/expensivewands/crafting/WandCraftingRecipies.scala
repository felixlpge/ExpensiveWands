package de.felixlpge.expensivewands.crafting

import de.felixlpge.expensivewands.{RegistrationHandler, expensivewands}
import net.minecraft.init.Items

object WandCraftingRecipies {

  var recipies: List[WandCraftingRecipe] = List[WandCraftingRecipe]()

  def addRecipe(wandCraftingRecipe: WandCraftingRecipe): Unit ={
    recipies ::= wandCraftingRecipe
  }




  private[expensivewands] def addDefaultRecipies(): Unit = {
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandWaterBreathing, Array(Items.STICK, Items.BUCKET, Items.IRON_INGOT), 0, 10000))
  }



}
