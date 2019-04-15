package de.felixlpge.expensivewands.blocks

import java.util.Random

import cofh.core.util.helpers.WrenchHelper
import de.felixlpge.expensivewands.{RegistrationHandler, expensivewands}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.util.math.{AxisAlignedBB, BlockPos}
import net.minecraft.world.World

class BlockPress extends BlockBase(Material.ANVIL){
  setRegistryName("press")
  setUnlocalizedName("press")
  setHardness(0.6F)
  setHarvestLevel("pickaxe", 2)
  setCreativeTab(expensivewands.creativeTab)

  override def createTileEntity(world: World, state: IBlockState): TileEntity = {
    super.createTileEntity(world, state)
    new TileEntityBlockPress
  }


  override def hasTileEntity(state: IBlockState): Boolean = {
    super.hasTileEntity(state)
    true
  }


  override def getBoundingBox(state: _root_.net.minecraft.block.state.IBlockState, source: _root_.net.minecraft.world.IBlockAccess, pos: _root_.net.minecraft.util.math.BlockPos): _root_.net.minecraft.util.math.AxisAlignedBB = {
    new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D)
  }


  override def onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    val press = worldIn.getTileEntity(pos)
    val item = playerIn.getHeldItem(hand)
    if (!worldIn.isRemote &&  press != null && (!item.isEmpty || playerIn.isSneaking)){
      if (!playerIn.isSneaking) {
        val press = worldIn.getTileEntity(pos).asInstanceOf[TileEntityBlockPress]
        press.addItem(item)
        if (!playerIn.isCreative) {
          playerIn.setHeldItem(hand, new ItemStack(Items.AIR))
        }
      }else {
        dropItemsInside(worldIn, pos)
      }
      return true
    }
    false
  }

  override def isFullCube(state: IBlockState): Boolean = false

  override def isOpaqueCube(state: IBlockState): Boolean = false

  override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
    dropItemsInside(worldIn, pos)
    super.breakBlock(worldIn, pos, state)
  }

  override def getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = RegistrationHandler.blockPressItem

  def dropItemsInside(worldIn: World, pos: BlockPos): Unit = {
    if (worldIn.getTileEntity(pos) != null) {
      val press = worldIn.getTileEntity(pos).asInstanceOf[TileEntityBlockPress]
      val items = press.getItemsInside()
      if (items.length > 0) {
        for (i <- items.indices) {
          worldIn.spawnEntity(new EntityItem(worldIn, pos.getX, pos.getY, pos.getZ, items(i)))
        }
        press.clear()
      }
    }
  }

}
