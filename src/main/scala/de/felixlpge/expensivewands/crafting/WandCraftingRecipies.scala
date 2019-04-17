package de.felixlpge.expensivewands.crafting

import de.felixlpge.expensivewands.RegistrationHandler
import de.felixlpge.expensivewands.blocks.TileEntityBlockPress
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.Item

object WandCraftingRecipies {

  private[expensivewands] var recipies: List[WandCraftingRecipe] = List[WandCraftingRecipe]()

  val BASE = Items.STICK

  def addRecipe(wandCraftingRecipe: WandCraftingRecipe): Unit = {
    recipies ::= wandCraftingRecipe
  }

  def removeRecipe(wandCraftingRecipe: WandCraftingRecipe): Unit = {
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
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandWaterBreathing, Array(BASE, Items.BUCKET, Items.IRON_INGOT), 0, 5000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingII, Array(RegistrationHandler.wandCraftingI, Items.GOLD_INGOT), 0, 10000))
    //Tier 1 Recipes (can draw 50k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandSaturation, Array(BASE, Items.FISHING_ROD, Items.FISH, Items.COOKED_BEEF, Items.COOKED_CHICKEN, Items.COOKED_PORKCHOP, Items.BAKED_POTATO), 1, 10000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandNoFall, Array(BASE, Items.FEATHER, Items.GLASS_BOTTLE, Items.STRING), 1, 45000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandFireResistance, Array(BASE, Items.LAVA_BUCKET, Items.IRON_CHESTPLATE, Items.BREWING_STAND), 1, 40000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingIII, Array(RegistrationHandler.wandCraftingII, Items.DIAMOND), 1, 50000))
    //Tier 2 Recipes (can draw 150k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandSpeed, Array(BASE, Items.SUGAR, Items.BOW, Items.CLOCK), 2, 100000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandRocket, Array(BASE, Items.ELYTRA, Items.CHORUS_FRUIT, Items.CHORUS_FRUIT_POPPED), 2, 140000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingIV, Array(RegistrationHandler.wandCraftingIII, Items.NETHER_STAR), 2, 150000))
    //Tier 3 Recipes (can draw 500k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandRocket, Array(BASE, Items.ARMOR_STAND, Items.BLAZE_POWDER, Items.BLAZE_ROD, Items.BOAT, RegistrationHandler.wandNoFall), 3, 300000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandCraftingV, Array(RegistrationHandler.wandCraftingIV, Items.DRAGON_BREATH), 3, 500000))
    //Tier 4 Recipes (can draw 1000k)
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandFastRocket, Array(RegistrationHandler.wandRocket, Item.getItemFromBlock(Blocks.BEACON), RegistrationHandler.wandSpeed), 4, 600000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandSolar, Array(BASE, Items.FLOWER_POT, Items.BUCKET, Items.LAVA_BUCKET, Item.getItemFromBlock(Blocks.DRAGON_EGG)), 4, 1000000))
    addRecipe(new WandCraftingRecipe(RegistrationHandler.wandLuna, Array(BASE, Items.FLOWER_POT, Items.BUCKET, Items.WATER_BUCKET, Item.getItemFromBlock(Blocks.DRAGON_EGG)), 4, 1000000))

  }


}
