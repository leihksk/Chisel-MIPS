package MIPS_chisel

import chisel3._
import chisel3.util._

class Signnext() extends RawModule {
  val a = IO(Input(UInt(16.W)))
  val y = IO(Output(UInt(32.W)))

  y := Cat(Fill(16,a(15)),a)
}
