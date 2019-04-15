package de.felixlpge.expensivewands.items

import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.potion.{Potion, PotionEffect}
import net.minecraft.world.World

class WandPotionEffect(effect: String, capacity: java.lang.Integer, drawRF: java.lang.Integer, amplifier: java.lang.Integer) extends WandActivable(capacity, drawRF) {
  setUnlocalizedName("wand_" + effect)
  setRegistryName("wand_" + effect)

  override def onUpdate(stack: ItemStack, worldIn: World, entityIn: Entity, itemSlot: Int, isSelected: Boolean): Unit = {
    if (isActive(stack)) {
      entityIn match {
        case player: EntityPlayer =>
          player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:" + effect), 5, amplifier, true, false))
        case _ =>
      }
    }
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected)
  }

}
