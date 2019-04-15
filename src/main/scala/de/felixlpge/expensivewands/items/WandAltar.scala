package de.felixlpge.expensivewands.items

import de.felixlpge.expensivewands.RegistrationHandler
import de.felixlpge.expensivewands.blocks.TileEntityBlockPress
import de.felixlpge.expensivewands.crafting.WandCraftingRecipies
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.{EnumActionResult, EnumFacing, EnumHand}
import net.minecraft.world.World

class WandAltar(capacity: java.lang.Integer, tier: java.lang.Integer, name: java.lang.String) extends WandBase(capacity){
  setUnlocalizedName("wand_crafting_" + name)
  setRegistryName("wand_crafting_" + name)

  override def onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    if (!worldIn.isRemote && !player.getHeldItem(hand).isEmpty && player.isSneaking ){
      val item = player.getHeldItem(hand)
      if (worldIn.getBlockState(pos).getBlock == RegistrationHandler.blockPress && worldIn.getTileEntity(pos) != null){
        // Getting press inventory from world
        val press = worldIn.getTileEntity(pos).asInstanceOf[TileEntityBlockPress]
        // Searching for overlapping recipe
        val craftingRecipe = WandCraftingRecipies.findOverlappingRecipe(press, tier)
        // If not found show message
        if (craftingRecipe != null ) {
          if (hasEnergy(item, craftingRecipe.getPowerUse)) {
            worldIn.spawnEntity(new EntityItem(worldIn, player.posX, player.posY, player.posZ, new ItemStack(craftingRecipe.getOutput)))
            press.clear()
            extractEnergy(item, craftingRecipe.getPowerUse, simulate = false)
            return EnumActionResult.SUCCESS
          }else {
            player.sendStatusMessage(new TextComponentTranslation("expensivewands.recipe.not_enough_energy"), true)
            return EnumActionResult.FAIL
          }
        } else {
          player.sendStatusMessage(new TextComponentTranslation("expensivewands.recipe.not_found"), true)
          return EnumActionResult.FAIL
        }
      }
    }
    super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ)
  }

}
