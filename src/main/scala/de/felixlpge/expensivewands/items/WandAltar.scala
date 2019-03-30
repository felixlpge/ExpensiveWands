package de.felixlpge.expensivewands.items

import de.felixlpge.expensivewands.RegistrationHandler
import de.felixlpge.expensivewands.blocks.TileEntityBlockPress
import de.felixlpge.expensivewands.crafting.WandCraftingRecipies
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumActionResult, EnumFacing, EnumHand}
import net.minecraft.world.World

class WandAltar(capacity: java.lang.Integer, tier: java.lang.Integer, name: java.lang.String) extends WandBase(capacity){
  setUnlocalizedName("wand_crafting_" + name)
  setRegistryName("wand_crafting_" + name)

  override def onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    if (!worldIn.isRemote && !player.getHeldItem(hand).isEmpty && !player.isSneaking){
      val item = player.getHeldItem(hand)
      if (worldIn.getBlockState(pos).getBlock == RegistrationHandler.blockPress && worldIn.getTileEntity(pos) != null){
        val tile = worldIn.getTileEntity(pos).asInstanceOf[TileEntityBlockPress]
        val craftingRecipe = WandCraftingRecipies.findOverlappingRecipe(tile, tier)
        if (craftingRecipe != null && hasEnergy(item, craftingRecipe.getPowerUse)) {
          player.addItemStackToInventory(new ItemStack(craftingRecipe.getOutput, 1))
          tile.clear()
          extractEnergy(item, craftingRecipe.getPowerUse, simulate = false)
          return EnumActionResult.SUCCESS
        } else {
          return EnumActionResult.FAIL
        }
      }
    }
    EnumActionResult.FAIL
  }

}
