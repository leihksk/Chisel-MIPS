package MIPS_chisel

import chisel3._
import chisel3.util._

class Flopenr(n:Int = 8) extends Module {
  val en = IO(Input(Bool()))
  val d = IO(Input(UInt(n.W)))
  val q = IO(Output(UInt(n.W)))

  val reg = RegEnable(d,0.U,en)
  q := reg
}
