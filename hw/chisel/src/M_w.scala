package MIPS_chisel

import chisel3._
import chisel3.util._

class M_w() extends Module {
  val writeregM  = IO(Input(UInt(5.W)))
  val aluoutM    = IO(Input(UInt(32.W)))
  val readdataM  = IO(Input(UInt(32.W)))
  val writeregW  = IO(Output(UInt(5.W)))
  val aluoutW    = IO(Output(UInt(32.W)))
  val readdataW  = IO(Output(UInt(32.W)))

  val reg1 = Reg(UInt(5.W))
  reg1 := writeregM
  writeregW := reg1
  val reg2 = Reg(UInt(32.W))
  reg2 := aluoutM
  aluoutW := reg2
  val reg3 = Reg(UInt(32.W))
  reg3 := readdataM
  readdataW := reg3

}
