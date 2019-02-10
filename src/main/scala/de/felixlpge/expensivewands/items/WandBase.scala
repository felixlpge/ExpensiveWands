package de.felixlpge.expensivewands.items

import cofh.core.util.helpers.NBTHelper
import cofh.redstoneflux.api.IEnergyContainerItem
import de.felixlpge.expensivewands.capability.{CapabilityProviderEnergy, EnergyConversionStorage}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.energy.{CapabilityEnergy, IEnergyStorage}
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack
import net.minecraft.util.math.MathHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy
/**
  * Class Wand Base
  *  Used by all Wands
  *
  */
class WandBase extends Item with IEnergyContainerItem {
  setCreativeTab(CreativeTabs.BREWING)

  val TAG_ENERGY = "energy"

  override def showDurabilityBar(itemStack: ItemStack) = true

  override def getDurabilityForDisplay(container: ItemStack): Double = {
    //TODO Show detailed power
    (this.getMaxEnergyStored(container) - this.getEnergyStored(container)) / this.getMaxEnergyStored(container)
  }

  override def receiveEnergy(container: ItemStack, maxReceive: Int, simulate: Boolean): Int = {
    var energy = this.getEnergyStored(container)
    var energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, maxReceive)
    if (!simulate) {
      energy += energyReceived
      NBTHelper.setInt(container, TAG_ENERGY, energy)
    }
    energyReceived
  }

  override def extractEnergy(container: ItemStack, maxExtract: Int, simulate: Boolean): Int = {
    var energy = this.getEnergyStored(container)
    val energyExtracted = Math.min(energy, maxExtract)
    if (!simulate) {
      energy -= energyExtracted
      NBTHelper.setInt(container, TAG_ENERGY, energy)
    }
    energyExtracted
  }

  override def getEnergyStored(container: ItemStack): Int = NBTHelper.getInt(container, TAG_ENERGY, 0)

  override def getMaxEnergyStored(container: ItemStack): Int = 50000

  override def initCapabilities(stack: ItemStack, nbt: NBTTagCompound) = new CapabilityProviderEnergy[IEnergyStorage](new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null)
}
