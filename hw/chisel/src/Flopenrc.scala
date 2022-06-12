package MIPS_chisel

import chisel3._
import chisel3.util._

class Flopenrc(n:Int = 8) extends Module {
  val en    = IO(Input(Bool()))
  val flush = IO(Input(Bool()))
  val d     = IO(Input(UInt(n.W)))
  val old_q = IO(Input(UInt(n.W)))
  val q     = IO(Output(UInt(n.W)))

  
  val reg = Mux(en,d,old_q)
  val care = Mux(flush,0.U,reg)
  q := RegNext(care,0.U)
}
