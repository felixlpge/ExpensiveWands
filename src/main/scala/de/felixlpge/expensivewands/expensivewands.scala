package de.felixlpge.expensivewands

import de.felixlpge.expensivewands.blocks.BlockPress
import de.felixlpge.expensivewands.crafting.WandCraftingRecipies
import de.felixlpge.expensivewands.items.special.WandRocket
import de.felixlpge.expensivewands.items.{WandPotionEffect, WandTime}
import de.felixlpge.expensivewands.proxy.CommonProxy
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

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
  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    event.getRegistry.registerAll(wandWaterBreathing, wandFireResistance, wandSaturation,
      wandSolar, wandLuna, wandRocket, blockPressItem)
    expensivewands.proxy.registerItemRenderer(wandWaterBreathing, 0, "wand_water_breathing")
    expensivewands.proxy.registerItemRenderer(wandFireResistance, 0, "wand_fire_resistance")
    expensivewands.proxy.registerItemRenderer(wandSaturation, 0, "wand_saturation")
    expensivewands.proxy.registerItemRenderer(wandSolar, 0, "wand_solar")
    expensivewands.proxy.registerItemRenderer(wandLuna, 0, "wand_luna")
    expensivewands.proxy.registerItemRenderer(wandRocket, 0, "wand_rocket")
    expensivewands.proxy.registerItemRenderer(blockPressItem, 0, "block_press")
  }

  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    event.getRegistry.register(blockPress)
  }
}