package de.felixlpge.expensivewands.crafting

import de.felixlpge.expensivewands.blocks.TileEntityBlockPress
import de.felixlpge.expensivewands.{RegistrationHandler, expensivewands}
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item

object WandCraftingRecipies {

  private var recipies: List[WandCraftingRecipe] = List[WandCraftingRecipe]()

  def addRecipe(wandCraftingRecipe: WandCraftingRecipe): Unit ={
    recipies ::= wandCraftingRecipe
  }

  def removeRecipe(wandCraftingRecipe: WandCraftingRecipe): Unit ={
    recipies = recipies.filter(!_.equals(wandCraftingRecipe))
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
    //Tier 0 Recipes (can draw 10k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandWaterBreathing, Array(Items.STICK, Items.BUCKET, Items.IRON_INGOT), 0, 5000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingII, Array(RegistrationHandler.wandCraftingI, Items.GOLD_INGOT), 0, 1000))
    //Tier 1 Recipes (can draw 50k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandSaturation, Array(Items.STICK, Items.FISHING_ROD, Items.FISH, Items.COOKED_BEEF, Items.COOKED_CHICKEN, Items.COOKED_PORKCHOP, Items.BAKED_POTATO), 1, 10000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandFireResistance, Array(Items.STICK, Items.LAVA_BUCKET, Items.IRON_CHESTPLATE, Items.BREWING_STAND), 1, 400000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingIII, Array(RegistrationHandler.wandCraftingII, Items.DIAMOND), 1, 50000))
    //Tier 2 Recipes (can draw 150k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandRocket, Array(Items.STICK, Items.ELYTRA, Items.CHORUS_FRUIT, Items.CHORUS_FRUIT_POPPED), 2, 100000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingIV, Array(RegistrationHandler.wandCraftingIII, Items.NETHER_STAR), 2, 150000))
    //Tier 3 Recipes (can draw 500k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandRocket, Array(Items.STICK, Items.ARMOR_STAND, Items.BLAZE_POWDER, Items.BLAZE_ROD, Items.BOAT), 3, 300000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingV, Array(RegistrationHandler.wandCraftingIV, Items.DRAGON_BREATH), 3, 500000))
    //Tier 4 Recipes (can draw 1000k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandSolar, Array(Items.STICK, Items.FLOWER_POT, Items.BUCKET, Items.LAVA_BUCKET, Items.NETHER_STAR), 4, 750000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandLuna, Array(Items.STICK, Items.FLOWER_POT, Items.BUCKET, Items.WATER_BUCKET, Items.NETHER_STAR), 4, 750000))

  }



}
