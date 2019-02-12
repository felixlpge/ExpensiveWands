package de.felixlpge.expensivewands.items
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.{ActionResult, EnumHand}
import net.minecraft.world.World

class WandTime(time: java.lang.Long, name: java.lang.String) extends WandBase(1000000) {
  setUnlocalizedName("wand_" + name)
  setRegistryName("wand_" + name)
  override def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    if (!worldIn.isRemote && !playerIn.getHeldItem(handIn).isEmpty){
      if (extractEnergy(playerIn.getHeldItem(handIn), 100000, simulate = true) == 100000){
        extractEnergy(playerIn.getHeldItem(handIn), 100000, simulate = false)
        worldIn.setWorldTime(time)
      }
    }
    super.onItemRightClick(worldIn, playerIn, handIn)
  }
}
