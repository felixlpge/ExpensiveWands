package de.felixlpge.expensivewands

import de.felixlpge.expensivewands.items.special.WandRocket
import de.felixlpge.expensivewands.items.{WandPotionEffect, WandTime}
import de.felixlpge.expensivewands.proxy.CommonProxy
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = expensivewands.MODID, version = expensivewands.VERSION, modLanguage = "scala", name = "Expensive Wands")
object expensivewands {
  final val MODID = "expensivewands"
  final val VERSION = "0.1"
  final val debug = false

  import net.minecraftforge.fml.common.SidedProxy

  @SidedProxy(serverSide = "de.felixlpge.expensivewands.proxy.CommonProxy", clientSide = "de.felixlpge.expensivewands.proxy.ClientProxy")
  var proxy: CommonProxy = _

}
@Mod.EventBusSubscriber(modid = expensivewands.MODID)
object RegistrationHandler{

  var wandWaterBreathing: WandPotionEffect = new WandPotionEffect("water_breathing", 50000, 20, 5)
  var wandFireResistance: WandPotionEffect = new WandPotionEffect("fire_resistance", 70000, 50, 5)
  var wandSaturation: WandPotionEffect = new WandPotionEffect("saturation", 150000, 1, 1)

  var wandSolar: WandTime = new WandTime(6000.toLong, "solar")
  var wandLuna: WandTime = new WandTime(18000.toLong, "luna")
  var wandRocket: WandRocket = new WandRocket
  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    event.getRegistry.registerAll(wandWaterBreathing, wandFireResistance, wandSaturation, wandSolar, wandLuna, wandRocket)
    expensivewands.proxy.registerItemRenderer(wandWaterBreathing, 0, "wand_water_breathing")
    expensivewands.proxy.registerItemRenderer(wandFireResistance, 0, "wand_fire_resistance")
    expensivewands.proxy.registerItemRenderer(wandSaturation, 0, "wand_saturation")
    expensivewands.proxy.registerItemRenderer(wandSolar, 0, "wand_solar")
    expensivewands.proxy.registerItemRenderer(wandLuna, 0, "wand_luna")
    expensivewands.proxy.registerItemRenderer(wandRocket, 0, "wand_rocket")
  }
}