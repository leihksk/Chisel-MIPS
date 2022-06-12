package MIPS_chisel

import chisel3._
import chisel3.util._

class Adder() extends RawModule {
  val a = IO(Input(UInt(32.W)))
  val b = IO(Input(UInt(32.W)))
  val y  = IO(Output(UInt(32.W)))

  y := a + b
}
