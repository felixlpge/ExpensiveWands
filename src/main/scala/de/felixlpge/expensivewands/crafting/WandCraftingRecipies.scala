package de.felixlpge.expensivewands.crafting

import de.felixlpge.expensivewands.blocks.TileEntityBlockPress
import de.felixlpge.expensivewands.{RegistrationHandler, expensivewands}
import net.minecraft.init.Items
import net.minecraft.item.Item

object WandCraftingRecipies {

  var recipies: List[WandCraftingRecipe] = List[WandCraftingRecipe]()

  def addRecipe(wandCraftingRecipe: WandCraftingRecipe): Unit ={
    recipies ::= wandCraftingRecipe
  }

  def findOverlappingRecipe(press: TileEntityBlockPress, tier: java.lang.Integer): WandCraftingRecipe = {
    var items = List[Item]()
    for (item <- press.getItemsInside()) {
      items ::= item.getItem
    }
    for (recipe <- recipies) {
      if (recipe.doTierMatch(tier) && recipe.doItemsMatch(items.toArray)) return recipe
    }
    null
  }

  private[expensivewands] def addDefaultRecipies(): Unit = {
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandWaterBreathing, Array(Items.STICK, Items.BUCKET, Items.IRON_INGOT), 0, 10000))
  }



}
