package MIPS_chisel

import chisel3._
import chisel3.util._

class Mux3() extends RawModule {
  val rd        = IO(Input(UInt(32.W)))
  val resultW   = IO(Input(UInt(32.W)))
  val alusrcM   = IO(Input(UInt(32.W)))
  val forward   = IO(Input(UInt(2.W)))
  val muxresult = IO(Output(UInt(32.W)))

  muxresult := Mux(forward === "b10".U,alusrcM,Mux(forward === "b01".U,resultW,rd))
}
