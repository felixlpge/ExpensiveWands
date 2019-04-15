package de.felixlpge.expensivewands.util

class TimeChanger(time: java.lang.Long, worldIn: net.minecraft.world.World) extends Runnable {
  override def run(): Unit = {
    var runTimeChange = true
    var i = worldIn.getWorldTime
    while (runTimeChange) {
      if (i >= time && i <= time + 40) {
        runTimeChange = false
      }
      worldIn.setWorldTime(i)
      Thread.sleep(10)
      if (i >= 23500) i = 0
      i += 40
    }
  }
}
