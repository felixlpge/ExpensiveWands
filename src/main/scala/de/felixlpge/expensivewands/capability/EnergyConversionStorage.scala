package de.felixlpge.expensivewands.capability

import cofh.redstoneflux.api.IEnergyContainerItem
import net.minecraft.item.ItemStack
import net.minecraftforge.energy.IEnergyStorage

/*
  * This class is written (original in java) by Tomson124
  * https://github.com/Tomson124/SimplyJetpacks-2/blob/1.12/src/main/java/tonius/simplyjetpacks/capability/CapabilityProviderEnergy.java
  * It is under MIT License
  * So I am allowed to reuse it
  */
class EnergyConversionStorage(var itemRF: IEnergyContainerItem, var itemStack: ItemStack) extends IEnergyStorage {
  override def receiveEnergy(maxReceive: Int, simulate: Boolean): Int = itemRF.receiveEnergy(itemStack, maxReceive, simulate)

  override def extractEnergy(maxExtract: Int, simulate: Boolean): Int = itemRF.extractEnergy(itemStack, maxExtract, simulate)

  override def getEnergyStored: Int = itemRF.getEnergyStored(itemStack)

  override def getMaxEnergyStored: Int = itemRF.getMaxEnergyStored(itemStack)

  override def canExtract = true

  override def canReceive = true
}
