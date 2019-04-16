package de.felixlpge.expensivewands.jei

import java.util

import de.felixlpge.expensivewands.crafting.WandCraftingRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack


class JEIPressRecipe(recipe: WandCraftingRecipe) extends IRecipeWrapper {


  override def getIngredients(ingredients: IIngredients): Unit = {
    var items = new java.util.ArrayList[ItemStack]()
    for (i <- recipe.getInput.indices) {
      items.add(new ItemStack(recipe.getInput(i)))
    }
    var wand = new java.util.ArrayList[ItemStack]()
    wand.add(recipe.getItemWand)
    var inputs = new util.ArrayList[java.util.List[ItemStack]]()
    inputs.add(items)
    inputs.add(wand)
    ingredients.setInputLists(classOf[ItemStack], inputs)
    ingredients.setOutput(classOf[ItemStack], new ItemStack(recipe.getOutput))
  }

  override def getTooltipStrings(mouseX: Int, mouseY: Int): util.List[String] = {
    if (mouseX > 27 && mouseY > 27 && mouseX < 72 && mouseY < 72) {
      var list = new util.ArrayList[String]()
      list.add(recipe.getPowerUse + "RF")
      return list
    }
    null
  }
}
