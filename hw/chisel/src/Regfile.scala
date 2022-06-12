package MIPS_chisel

import chisel3._
import chisel3.util._

class Regfile() extends Module {
  val we3 = IO(Input(Bool()))
  val ra1 = IO(Input(UInt(5.W)))
  val ra2 = IO(Input(UInt(5.W)))
  val wa3 = IO(Input(UInt(5.W)))
  val wd3 = IO(Input(UInt(32.W)))
  val rd1 = IO(Output(UInt(32.W)))
  val rd2 = IO(Output(UInt(32.W)))

  val RF = RegInit(VecInit(Seq.fill(32)(0.U(32.W))))
  rd1 := Mux(ra1 =/= 0.U,RF(ra1),0.U)
  rd2 := Mux(ra2 =/= 0.U,RF(ra2),0.U)
  when(we3){
    RF(wa3) := wd3
  }
}
