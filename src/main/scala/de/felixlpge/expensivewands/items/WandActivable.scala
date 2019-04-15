package de.felixlpge.expensivewands.items

import cofh.core.util.helpers.NBTHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.MathHelper
import net.minecraft.util.{ActionResult, EnumHand}
import net.minecraft.world.World

class WandActivable(capacity: java.lang.Integer, drawRF: java.lang.Integer) extends WandBase(capacity) {


  override def getRGBDurabilityForDisplay(stack: ItemStack): Int = return MathHelper.hsvToRGB(Math.max(0.0F, (1.0F - getDurabilityForDisplay(stack)).toFloat) / (if (isActive(stack)) 50F else 3.0F), 1.0F, 1.0F)

  def activate(item: ItemStack): java.lang.Boolean ={
    if (hasEnergy(item, drawRF)){
      NBTHelper.setBoolean(item, "active", true)
      return true
    }
    false
  }

  def deActivate(item: ItemStack): Unit ={
    NBTHelper.setBoolean(item, "active", false)
  }

  def isActive(item: ItemStack): java.lang.Boolean ={
    NBTHelper.getBoolean(item, "active", false)
  }

  def toggleActive(item: ItemStack): java.lang.Boolean ={
    if (!isActive(item)){
      this.activate(item)
    }else{
      this.deActivate(item)
      false
    }
  }

  override def onUpdate(stack: ItemStack, worldIn: World, entityIn: Entity, itemSlot: Int, isSelected: Boolean): Unit = {
    if (isActive(stack) && hasEnergy(stack, drawRF)){
      extractEnergy(stack, drawRF, simulate = false)
    }else if (!hasEnergy(stack, drawRF)){
      deActivate(stack)
    }
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected)
  }

  override def onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult[ItemStack] = {
    if (!worldIn.isRemote && !playerIn.getHeldItem(handIn).isEmpty && !playerIn.isSneaking){
      toggleActive(playerIn.getHeldItem(handIn))
    }
    super.onItemRightClick(worldIn, playerIn, handIn)
  }

}
