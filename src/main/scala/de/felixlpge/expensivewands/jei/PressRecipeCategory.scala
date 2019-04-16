package de.felixlpge.expensivewands.jei

import de.felixlpge.expensivewands.expensivewands
import mezz.jei.api.gui.{IDrawable, IRecipeLayout}
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategory
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class PressRecipeCategory extends IRecipeCategory[JEIPressRecipe] {
  private val CRAFTING_WAND = 0
  private val RESULT_WAND = 1

  override def getUid: String = PressRecipeCategory.ID

  override def getTitle: String = "Wand Press"

  override def getModName: String = expensivewands.NAME

  override def getBackground: IDrawable = ExpensiveWandsJei.JEIHelper.getGuiHelper.createDrawable(new ResourceLocation(expensivewands.MODID, "gui/press_jei.png"), -5, -2, 130, 90, 120, 90)

  override def setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: JEIPressRecipe, ingredients: IIngredients): Unit = {
    val inputs = ingredients.getInputs(classOf[ItemStack])
    val distance = 110 / (inputs.get(0).size() - 1)
    for (i <- 0 until inputs.get(0).size()) {
      recipeLayout.getItemStacks.init(i + 2, true, 1 + i * distance, 80)
      recipeLayout.getItemStacks.set(i + 2, inputs.get(0).get(i))
    }
    recipeLayout.getItemStacks.init(CRAFTING_WAND, true, 69, 57)
    recipeLayout.getItemStacks.init(RESULT_WAND, false, 56, 4)
    val outputs = ingredients.getOutputs(classOf[ItemStack])
    recipeLayout.getItemStacks.set(RESULT_WAND, outputs.get(0).get(0))
    recipeLayout.getItemStacks.set(CRAFTING_WAND, inputs.get(1).get(0))
  }
}

object PressRecipeCategory {
  val ID = "expensivewands.jei.press"
}
