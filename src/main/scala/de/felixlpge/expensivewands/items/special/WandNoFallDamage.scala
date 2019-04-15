package de.felixlpge.expensivewands.items.special

import de.felixlpge.expensivewands.items.WandActivable
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class WandNoFallDamage extends WandActivable(1000000, 2) {
  setUnlocalizedName("wand_no_fall")
  setRegistryName("wand_no_fall")

  override def onUpdate(stack: ItemStack, worldIn: World, entityIn: Entity, itemSlot: Int, isSelected: Boolean): Unit = {
    if (isActive(stack)) {
      if (entityIn.isInstanceOf[EntityPlayer]) {
        entityIn.fallDistance = 0
      }
    }
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected)
  }
}
