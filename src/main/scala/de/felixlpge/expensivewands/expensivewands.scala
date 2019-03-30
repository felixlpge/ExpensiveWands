package de.felixlpge.expensivewands

import de.felixlpge.expensivewands.blocks.{BlockPress, TileEntityBlockPress}
import de.felixlpge.expensivewands.crafting.WandCraftingRecipies
import de.felixlpge.expensivewands.items.special.WandRocket
import de.felixlpge.expensivewands.items.{WandAltar, WandPotionEffect, WandTime}
import de.felixlpge.expensivewands.proxy.CommonProxy
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod(modid = expensivewands.MODID, version = expensivewands.VERSION, modLanguage = "scala", name = "Expensive Wands")
object expensivewands {
  final val MODID = "expensivewands"
  final val VERSION = "0.1"
  final val debug = false

  import net.minecraftforge.fml.common.SidedProxy

  @SidedProxy(serverSide = "de.felixlpge.expensivewands.proxy.CommonProxy", clientSide = "de.felixlpge.expensivewands.proxy.ClientProxy")
  var proxy: CommonProxy = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit ={
    WandCraftingRecipies.addDefaultRecipies()
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit ={
    GameRegistry.registerTileEntity(classOf[TileEntityBlockPress], "expensivewands:tileEntityBlockPress")
  }

}
@Mod.EventBusSubscriber(modid = expensivewands.MODID)
object RegistrationHandler{
  //Blocks
  var blockPress : BlockPress = new BlockPress
  var blockPressItem: ItemBlock = blockPress.createItemBlock
  //Wands Potion
  var wandWaterBreathing: WandPotionEffect = new WandPotionEffect("water_breathing", 50000, 20, 5)
  var wandFireResistance: WandPotionEffect = new WandPotionEffect("fire_resistance", 70000, 50, 5)
  var wandSaturation: WandPotionEffect = new WandPotionEffect("saturation", 150000, 1, 1)
  //Different wand
  var wandSolar: WandTime = new WandTime(6000.toLong, "solar")
  var wandLuna: WandTime = new WandTime(18000.toLong, "luna")
  var wandRocket: WandRocket = new WandRocket
  //Crafting wands
  var wandCraftingI: WandAltar = new WandAltar(10001, 0, "I")
  var wandCraftingII: WandAltar = new WandAltar(50001, 1, "II")
  var wandCraftingIII: WandAltar = new WandAltar(150001, 2, "III")
  var wandCraftingIV: WandAltar = new WandAltar(500001, 3,"IV")
  var wandCraftingV: WandAltar = new WandAltar(1000001, 4, "V")

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    event.getRegistry.registerAll(wandWaterBreathing, wandFireResistance, wandSaturation,
      wandSolar, wandLuna, wandRocket, blockPressItem,
      wandCraftingI, wandCraftingII, wandCraftingIII, wandCraftingIV, wandCraftingV)
    expensivewands.proxy.registerItemRenderer(wandWaterBreathing, 0, "wand_water_breathing")
    expensivewands.proxy.registerItemRenderer(wandFireResistance, 0, "wand_fire_resistance")
    expensivewands.proxy.registerItemRenderer(wandSaturation, 0, "wand_saturation")
    expensivewands.proxy.registerItemRenderer(wandSolar, 0, "wand_solar")
    expensivewands.proxy.registerItemRenderer(wandLuna, 0, "wand_luna")
    expensivewands.proxy.registerItemRenderer(wandRocket, 0, "wand_rocket")
    expensivewands.proxy.registerItemRenderer(blockPressItem, 0, "block_press")
    expensivewands.proxy.registerItemRenderer(wandCraftingI, 0, "wand_crafting_I")
    expensivewands.proxy.registerItemRenderer(wandCraftingII, 0, "wand_crafting_II")
    expensivewands.proxy.registerItemRenderer(wandCraftingIII, 0, "wand_crafting_III")
    expensivewands.proxy.registerItemRenderer(wandCraftingIV, 0, "wand_crafting_IV")
    expensivewands.proxy.registerItemRenderer(wandCraftingV, 0, "wand_crafting_V")

  }

  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    event.getRegistry.register(blockPress)
  }
}