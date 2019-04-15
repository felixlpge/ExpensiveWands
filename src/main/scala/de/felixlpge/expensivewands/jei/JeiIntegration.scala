package de.felixlpge.expensivewands.jei

import de.felixlpge.expensivewands.crafting.WandCraftingRecipies
import mezz.jei.api.{IModPlugin, IModRegistry, JEIPlugin}
import net.minecraft.item.ItemStack

@JEIPlugin
class JeiIntegration extends IModPlugin {
  override def register(registry: IModRegistry): Unit = {
    for (i <- WandCraftingRecipies.recipies.indices) {
      val recipe = WandCraftingRecipies.recipies(i)
      var text = Array[String]()
      text :+= "expensivewands.crafting.press"
      for (inputId <- recipe.getInput.indices) {
        text :+= recipe.getInput(inputId).getUnlocalizedName + ".name"
      }
      text :+= "expensivewands.crafting.press2"
      text :+= recipe.getNeededWand
      text :+= "expensivewands.crafting.press3"
      registry.addIngredientInfo(new ItemStack(recipe.getOutput), classOf[ItemStack], text: _*)
    }
  }
}
