package MIPS_chisel

import chisel3._
import chisel3.util._

class Comparator(n:Int = 8) extends RawModule {
  val a  = IO(Input(UInt(n.W)))
  val b  = IO(Input(UInt(n.W)))
  val e = IO(Output(UInt(1.W)))

  e := (a === b) 
}
