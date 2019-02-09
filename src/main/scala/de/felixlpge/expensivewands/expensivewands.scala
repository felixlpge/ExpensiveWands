package de.felixlpge.expensivewands

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

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {

  }
}