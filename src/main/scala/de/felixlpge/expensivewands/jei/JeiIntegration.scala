package de.felixlpge.expensivewands.jei

import de.felixlpge.expensivewands.RegistrationHandler
import de.felixlpge.expensivewands.crafting.{WandCraftingRecipe, WandCraftingRecipies}
import mezz.jei.api.recipe.{IRecipeCategoryRegistration, IRecipeWrapper, IRecipeWrapperFactory}
import mezz.jei.api.{IJeiHelpers, IModPlugin, IModRegistry, JEIPlugin}
import net.minecraft.item.ItemStack

import scala.collection.JavaConversions._

@JEIPlugin
class JeiIntegration extends IModPlugin {
  override def register(registry: IModRegistry): Unit = {
    ExpensiveWandsJei.JEIHelper = registry.getJeiHelpers
    /*for (i <- WandCraftingRecipies.recipies.indices) {
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
    } */
    registry.addRecipes(WandCraftingRecipies.recipies.toIterable, PressRecipeCategory.ID)
    registry.handleRecipes(classOf[WandCraftingRecipe], new IRecipeWrapperFactory[WandCraftingRecipe] {
      override def getRecipeWrapper(recipe: WandCraftingRecipe): IRecipeWrapper = new JEIPressRecipe(recipe)
    }, PressRecipeCategory.ID)
    registry.addRecipeCatalyst(new ItemStack(RegistrationHandler.blockPressItem), PressRecipeCategory.ID)

  }

  override def registerCategories(registry: IRecipeCategoryRegistration): Unit = {
    if (ExpensiveWandsJei.JEIHelper == null) {
      ExpensiveWandsJei.JEIHelper = registry.getJeiHelpers
    }
    registry.addRecipeCategories(
      new PressRecipeCategory
    )
  }
}

object ExpensiveWandsJei {
  var JEIHelper: IJeiHelpers = _
}