package de.felixlpge.expensivewands.items

import cofh.core.util.helpers.NBTHelper
import cofh.redstoneflux.api.IEnergyContainerItem
import de.felixlpge.expensivewands.capability.{CapabilityProviderEnergy, EnergyConversionStorage}
import de.felixlpge.expensivewands.expensivewands
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.energy.{CapabilityEnergy, IEnergyStorage}

/**
  * Class Wand Base
  * Used by all Wands
  *
  */
class WandBase(capacity: java.lang.Integer) extends Item with IEnergyContainerItem {
  setCreativeTab(expensivewands.creativeTab)
  setMaxStackSize(1)
  val TAG_ENERGY = "energy"


  override def getHighlightTip(item: ItemStack, displayName: String): String = {
    displayName + " (" + getEnergyStored(item) + "RF/" + getMaxEnergyStored(item) + "RF)"
  }

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
    if (!simulate && !expensivewands.debug) {
      energy -= energyExtracted
      NBTHelper.setInt(container, TAG_ENERGY, energy)
    }
    energyExtracted
  }

  def hasEnergy(container: ItemStack, count: java.lang.Integer): java.lang.Boolean = getEnergyStored(container) > count

  override def getEnergyStored(container: ItemStack): Int = {
    if (expensivewands.debug) {
      return getMaxEnergyStored(container)
    }
    NBTHelper.getInt(container, TAG_ENERGY, 0)
  }

  override def getMaxEnergyStored(container: ItemStack): Int = capacity

  override def initCapabilities(stack: ItemStack, nbt: NBTTagCompound) = new CapabilityProviderEnergy[IEnergyStorage](new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null)
}

