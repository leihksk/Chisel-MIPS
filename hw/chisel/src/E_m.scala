package MIPS_chisel

import chisel3._
import chisel3.util._

class E_m() extends Module {
  val writeregE  = IO(Input(UInt(5.W)))
  val aluoutE    = IO(Input(UInt(32.W)))
  val writedataE = IO(Input(UInt(32.W)))
  val writeregM  = IO(Output(UInt(5.W)))
  val aluoutM    = IO(Output(UInt(32.W)))
  val writedataM = IO(Output(UInt(32.W)))

  val reg1 = Reg(UInt(5.W))
  reg1 := writeregE
  writeregM := reg1
  val reg2 = Reg(UInt(32.W))
  reg2 := aluoutE
  aluoutM := reg2
  val reg3 = Reg(UInt(32.W))
  reg3 := writedataE
  writedataM := reg3

}
