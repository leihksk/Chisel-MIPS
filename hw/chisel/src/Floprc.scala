package MIPS_chisel

import chisel3._
import chisel3.util._

class Floprc(n:Int = 8) extends Module {
  val flush = IO(Input(Bool()))
  val d     = IO(Input(UInt(n.W)))
  val q     = IO(Output(UInt(n.W)))

  val care = Mux(flush,0.U,d)
  q := RegNext(care,0.U)

  //when(flush){q := 0.U}.otherwise{q := d}
}
