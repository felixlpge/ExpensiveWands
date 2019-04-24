package de.felixlpge.expensivewands

import de.felixlpge.expensivewands.blocks.{BlockPress, TileEntityBlockPress}
import de.felixlpge.expensivewands.crafting.WandCraftingRecipies
import de.felixlpge.expensivewands.items.special.{WandNoFallDamage, WandRocket}
import de.felixlpge.expensivewands.items.{WandAltar, WandPotionEffect, WandTime}
import de.felixlpge.expensivewands.proxy.CommonProxy
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraft.world.storage.loot.{LootEntryItem, LootTableList}
import net.minecraft.world.storage.loot.conditions.LootCondition
import net.minecraft.world.storage.loot.functions.LootFunction
import net.minecraftforge.event.{LootTableLoadEvent, RegistryEvent}
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod(modid = expensivewands.MODID, version = expensivewands.VERSION, modLanguage = "scala", name = expensivewands.NAME)
object expensivewands {
  final val MODID = "expensivewands"
  final val VERSION = "0.1"
  final val NAME = "Expensive Wands"
  final val debug = false

  //Creative tab
  var creativeTab: CreativeTabs = new CreativeTabs(MODID) {
    override def getTabIconItem: ItemStack = new ItemStack(RegistrationHandler.wandSolar)
  }

  import net.minecraftforge.fml.common.SidedProxy

  @SidedProxy(serverSide = "de.felixlpge.expensivewands.proxy.CommonProxy", clientSide = "de.felixlpge.expensivewands.proxy.ClientProxy")
  var proxy: CommonProxy = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    WandCraftingRecipies.addDefaultRecipies()
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    GameRegistry.registerTileEntity(classOf[TileEntityBlockPress], "expensivewands:tileEntityBlockPress")
  }

}

@Mod.EventBusSubscriber(modid = expensivewands.MODID)
object Handler {
  @SubscribeEvent
  def onLootTablesLoaded(event: LootTableLoadEvent): Unit = {
    val pool1 = event.getTable.getPool("pool1")
    if (pool1 != null) {
      if (event.getName == LootTableList.CHESTS_VILLAGE_BLACKSMITH) {
        pool1.addEntry(new LootEntryItem(RegistrationHandler.wandSpeed, 3, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:wandSpeed"))
        pool1.addEntry(new LootEntryItem(RegistrationHandler.blockPressItem, 5, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:press"))
      }
      else if (event.getName == LootTableList.CHESTS_NETHER_BRIDGE) {
        pool1.addEntry(new LootEntryItem(RegistrationHandler.wandFireResistance, 5, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:wandFireRessistance"))
      }
      else if (event.getName == LootTableList.CHESTS_END_CITY_TREASURE) {
        pool1.addEntry(new LootEntryItem(RegistrationHandler.wandRocket, 3, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:wandRocket"))
        pool1.addEntry(new LootEntryItem(RegistrationHandler.wandLuna, 1, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:wandLuna"))
      }
      else if (event.getName.toString.contains("chests")) {
        pool1.addEntry(new LootEntryItem(RegistrationHandler.wandCraftingI, 6, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:craftingWand"))
        pool1.addEntry(new LootEntryItem(RegistrationHandler.wandSaturation, 3, 0, new Array[LootFunction](0), new Array[LootCondition](0), "loottable:wandSaturation"))
      }
    }
  }
}

@Mod.EventBusSubscriber(modid = expensivewands.MODID)
object RegistrationHandler {
  //Blocks
  var blockPress: BlockPress = new BlockPress
  var blockPressItem: ItemBlock = blockPress.createItemBlock
  //Wands Potion
  var wandWaterBreathing: WandPotionEffect = new WandPotionEffect("water_breathing", 50000, 20, 5)
  var wandFireResistance: WandPotionEffect = new WandPotionEffect("fire_resistance", 70000, 50, 5)
  var wandSaturation: WandPotionEffect = new WandPotionEffect("saturation", 150000, 1, 1)
  var wandSpeed: WandPotionEffect = new WandPotionEffect("speed", 100000, 30, 4)
  //Different wand
  var wandSolar: WandTime = new WandTime(6000.toLong, "solar")
  var wandLuna: WandTime = new WandTime(18000.toLong, "luna")
  var wandNoFall: WandNoFallDamage = new WandNoFallDamage
  //TODO Add Weather (Thunder, clear)
  //TODO (Portable) Ender Chest
  //TODO Lighthning (Click, 10 sec, strike)
  //TODO Langsamer fallen Stab
  var wandRocket: WandRocket = new WandRocket("rocket", 250, 0.4)
  var wandFastRocket: WandRocket = new WandRocket("fast_rocket", 600, 1.3)
  //Crafting wands
  var wandCraftingI: WandAltar = new WandAltar(10001, 0, "I")
  var wandCraftingII: WandAltar = new WandAltar(50001, 1, "II")
  var wandCraftingIII: WandAltar = new WandAltar(150001, 2, "III")
  var wandCraftingIV: WandAltar = new WandAltar(500001, 3, "IV")
  var wandCraftingV: WandAltar = new WandAltar(1000001, 4, "V")
  //Mob wands


  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    // Set extra settings
    blockPressItem.setMaxStackSize(1)
    blockPressItem.setCreativeTab(expensivewands.creativeTab)
    //register
    event.getRegistry.registerAll(blockPressItem, wandCraftingI, wandCraftingII, wandCraftingIII, wandCraftingIV, wandCraftingV,
      wandSpeed, wandWaterBreathing, wandFireResistance, wandSaturation,
      wandSolar, wandLuna, wandRocket, wandFastRocket, wandNoFall)
    expensivewands.proxy.registerItemRenderer(wandWaterBreathing, 0, "wand_water_breathing")
    expensivewands.proxy.registerItemRenderer(wandFireResistance, 0, "wand_fire_resistance")
    expensivewands.proxy.registerItemRenderer(wandSaturation, 0, "wand_saturation")
    expensivewands.proxy.registerItemRenderer(wandSolar, 0, "wand_solar")
    expensivewands.proxy.registerItemRenderer(wandLuna, 0, "wand_luna")
    expensivewands.proxy.registerItemRenderer(wandRocket, 0, "wand_rocket")
    expensivewands.proxy.registerItemRenderer(wandFastRocket, 0, "wand_fast_rocket")
    expensivewands.proxy.registerItemRenderer(blockPressItem, 0, "block_press")
    expensivewands.proxy.registerItemRenderer(wandCraftingI, 0, "wand_crafting_I")
    expensivewands.proxy.registerItemRenderer(wandCraftingII, 0, "wand_crafting_II")
    expensivewands.proxy.registerItemRenderer(wandCraftingIII, 0, "wand_crafting_III")
    expensivewands.proxy.registerItemRenderer(wandCraftingIV, 0, "wand_crafting_IV")
    expensivewands.proxy.registerItemRenderer(wandCraftingV, 0, "wand_crafting_V")
    expensivewands.proxy.registerItemRenderer(wandNoFall, 0, "wand_no_fall")
    expensivewands.proxy.registerItemRenderer(wandSpeed, 0, "wand_speed")
  }

  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    event.getRegistry.register(blockPress)
  }

}