package de.felixlpge.expensivewands.items.special

import de.felixlpge.expensivewands.items.WandActivable
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.MathHelper
import net.minecraft.util.{ActionResult, EnumHand}
import net.minecraft.world.World

class WandRocket extends WandActivable(1000000, 250) {
  setUnlocalizedName("wand_rocket")
  setRegistryName("wand_rocket")

  override def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    playerIn.fallDistance = 0
    super.onItemRightClick(worldIn, playerIn, handIn)
  }

  override def onUpdate(stack: ItemStack, worldIn: World, entityIn: Entity, itemSlot: Int, isSelected: Boolean): Unit = {
    if (isActive(stack) && worldIn.isRemote) {
      entityIn match {
        case player: EntityPlayer =>
          val yaw = player.rotationYaw
          val pitch = player.rotationPitch
          val f = 1.0F
          val motionX = (-MathHelper.sin(yaw / 180.0F * Math.PI.toFloat) * MathHelper.cos(pitch / 180.0F * Math.PI.toFloat) * f).asInstanceOf[Double] * 0.4
          val motionZ = (MathHelper.cos(yaw / 180.0F * Math.PI.toFloat) * MathHelper.cos(pitch / 180.0F * Math.PI.toFloat) * f).asInstanceOf[Double] * 0.4
          player.setVelocity(motionX, 0.3, motionZ)
        case _ =>
      }
    }
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected)
  }

}
