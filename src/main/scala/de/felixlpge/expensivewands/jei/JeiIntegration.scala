package de.felixlpge.expensivewands.jei

import de.felixlpge.expensivewands.crafting.{WandCraftingRecipe, WandCraftingRecipies}
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.{IJeiHelpers, IJeiRuntime, IModPlugin, IModRegistry, JEIPlugin}
import net.minecraft.item.ItemStack

import scala.collection.JavaConverters._

@JEIPlugin
class JeiIntegration extends IModPlugin{
  override def register(registry: IModRegistry): Unit = {
    for (recipe <- WandCraftingRecipies.recipies) {
      var text = Array[String]()
      text :+= "expensivewands.crafting.press"
      for (input <- recipe.getInput) {
        text :+= input.getUnlocalizedName + ".name"
      }
      text :+= "expensivewands.crafting.press2"
      text :+= recipe.getNeededWand
      text :+= "expensivewands.crafting.press3"
      registry.addIngredientInfo(new ItemStack(recipe.getOutput), classOf[ItemStack], text : _*)
    }
  }
}
