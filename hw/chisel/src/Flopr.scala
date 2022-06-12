package MIPS_chisel

import chisel3._
import chisel3.util._

class Flopr(n:Int = 8) extends Module {
  val d = IO(Input(UInt(n.W)))
  val q = IO(Output(UInt(n.W)))

  //val reg = RegNext(d,0.U)
  q := RegNext(d,0.U)
}
