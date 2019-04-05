package de.felixlpge.expensivewands.items
import de.felixlpge.expensivewands.util.TimeChanger
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.{ActionResult, EnumHand}
import net.minecraft.world.World

class WandTime(time: java.lang.Long, name: java.lang.String) extends WandBase(1000000) {
  setUnlocalizedName("wand_" + name)
  setRegistryName("wand_" + name)
  override def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    if (!playerIn.getHeldItem(handIn).isEmpty && !playerIn.isSneaking){
      val cost = calculateDelta(worldIn.getWorldTime, time) * 10
      if (hasEnergy(playerIn.getHeldItem(handIn), cost)){
        extractEnergy(playerIn.getHeldItem(handIn), cost, simulate = false)
        new Thread(new TimeChanger(time, worldIn)).start()
      }
    }
    super.onItemRightClick(worldIn, playerIn, handIn)
  }

  private def calculateDelta(start: Long, end: Long): Int = {
    var calculate = true
    var calculated = 0
    var courser = start
    while (calculate) {
      if (courser != end) {
        calculated += 1
        if (courser != 24000) {
          courser += 1
        } else {
          courser = 0
        }
      } else {
        calculate = false
      }
    }
    calculated
  }
}