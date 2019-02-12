package de.felixlpge.expensivewands.items

import cofh.core.util.helpers.NBTHelper
import cofh.redstoneflux.api.IEnergyContainerItem
import de.felixlpge.expensivewands.capability.{CapabilityProviderEnergy, EnergyConversionStorage}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.energy.{CapabilityEnergy, IEnergyStorage}
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy
/**
  * Class Wand Base
  *  Used by all Wands
  *
  */
class WandBase(capacity: java.lang.Integer) extends Item with IEnergyContainerItem {
  setCreativeTab(CreativeTabs.BREWING)
  val TAG_ENERGY = "energy"

  override def showDurabilityBar(itemStack: ItemStack) = true

  override def getDurabilityForDisplay(container: ItemStack): Double = {
    (this.getMaxEnergyStored(container).toDouble - this.getEnergyStored(container).toDouble) / this.getMaxEnergyStored(container).toDouble
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

  def hasEnergy(container: ItemStack, count: java.lang.Integer): java.lang.Boolean = getEnergyStored(container) > count

  override def getEnergyStored(container: ItemStack): Int = NBTHelper.getInt(container, TAG_ENERGY, 0)

  override def getMaxEnergyStored(container: ItemStack): Int = capacity

  override def initCapabilities(stack: ItemStack, nbt: NBTTagCompound) = new CapabilityProviderEnergy[IEnergyStorage](new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null)
}

