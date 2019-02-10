package de.felixlpge.expensivewands.capability

import javax.annotation.Nullable
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.{Capability, ICapabilityProvider}

/*
  * This class is written (original in java) by Tomson124
  * https://github.com/Tomson124/SimplyJetpacks-2/blob/1.12/src/main/java/tonius/simplyjetpacks/capability/CapabilityProviderEnergy.java
  * It is under MIT License
  * So I am allowed to reuse it
  */

class CapabilityProviderEnergy[HANDLER](val instance: HANDLER, val capability: Capability[HANDLER], val facing: EnumFacing) extends ICapabilityProvider {
  def this(capability: Capability[HANDLER], @Nullable facing: EnumFacing) {
    this(capability.getDefaultInstance, capability, facing)
  }

  override def hasCapability(capability: Capability[_], @Nullable facing: EnumFacing): Boolean = capability eq getCapability

  override def getCapability[T](capability: Capability[T], @Nullable facing: EnumFacing): T = {
    if (capability eq getCapability) return getCapability.cast(getInstance)
    null.asInstanceOf[T]
  }


  final def getCapability: Capability[HANDLER] = capability

  @Nullable def getFacing: EnumFacing = facing

  final def getInstance: HANDLER = instance
}