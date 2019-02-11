package de.felixlpge.expensivewands

import de.felixlpge.expensivewands.items.WandPotionEffect
import de.felixlpge.expensivewands.proxy.CommonProxy
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = expensivewands.MODID, version = expensivewands.VERSION, modLanguage = "scala", name = "Expensive Wands")
object expensivewands {
  final val MODID = "expensivewands"
  final val VERSION = "0.1"

  import net.minecraftforge.fml.common.SidedProxy

  @SidedProxy(serverSide = "de.felixlpge.expensivewands.proxy.CommonProxy", clientSide = "de.felixlpge.expensivewands.proxy.ClientProxy")
  var proxy: CommonProxy = _

}
@Mod.EventBusSubscriber(modid = expensivewands.MODID)
object RegistrationHandler{

  var wandWaterBreathing: WandPotionEffect = new WandPotionEffect("water_breathing", 50000, 20)
  var wandFireResistance: WandPotionEffect = new WandPotionEffect("fire_resistance", 70000, 50)
  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    event.getRegistry.registerAll(wandWaterBreathing, wandFireResistance)
    expensivewands.proxy.registerItemRenderer(wandWaterBreathing, 0, "wand_water_breathing")
    expensivewands.proxy.registerItemRenderer(wandFireResistance, 0, "wand_fire_resistance")
  }
}