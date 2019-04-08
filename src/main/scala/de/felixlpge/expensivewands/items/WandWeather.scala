package de.felixlpge.expensivewands.items

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.{ActionResult, EnumHand}
import net.minecraft.world.World

class WandWeather(weather: String) extends WandBase(100000) {

  override def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    if (!worldIn.isRemote && !playerIn.getHeldItem(handIn).isEmpty && !playerIn.isSneaking) {
      val item = playerIn.getHeldItem(handIn)
      if (hasEnergy(item, 50000)) {
        val worldinfo = worldIn.getWorldInfo
        if (weather == "clear") {
          worldinfo.setCleanWeatherTime(10000)
          worldinfo.setRainTime(0)
          worldinfo.setThunderTime(0)
          worldinfo.setRaining(false)
          worldinfo.setThundering(false)
        }else if (weather == "rain") {
          worldinfo.setCleanWeatherTime(0)
          worldinfo.setRainTime(5000)
          worldinfo.setThunderTime(5000)
          worldinfo.setRaining(true)
          worldinfo.setThundering(false)
        }else if (weather == "thunder") {
          worldinfo.setCleanWeatherTime(0)
          worldinfo.setRainTime(3000)
          worldinfo.setThunderTime(3000)
          worldinfo.setRaining(true)
          worldinfo.setThundering(true)
        }
        extractEnergy(item, 50000, false)
      }
    }
    super.onItemRightClick(worldIn, playerIn, handIn)
  }

}
