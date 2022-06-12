package MIPS_chisel

import chisel3._
import chisel3.util._

class Mux2(n:Int = 8) extends RawModule {
  val s  = IO(Input(UInt(1.W)))
  val d0 = IO(Input(UInt(n.W)))
  val d1 = IO(Input(UInt(n.W)))
  val y  = IO(Output(UInt(n.W)))

  y := Mux(s.asBool, d1, d0)
}
