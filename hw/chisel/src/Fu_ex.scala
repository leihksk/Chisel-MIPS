package MIPS_chisel

import chisel3._
import chisel3.util._

class Fu_ex() extends RawModule {
  val regwriteW = IO(Input(Bool()))
  val regwriteM = IO(Input(Bool()))
  val writeregW = IO(Input(UInt(5.W)))
  val writeregM = IO(Input(UInt(5.W)))
  val rsE       = IO(Input(UInt(5.W)))
  val rtE       = IO(Input(UInt(5.W)))

  val forwardAE = IO(Output(UInt(2.W)))
  val forwardBE = IO(Output(UInt(2.W)))

  forwardAE := "b00".U
  forwardBE := "b00".U
  forwardAE := Mux(regwriteM & rsE =/= "b00000".U & writeregM === rsE,"b10".U,Mux(regwriteW & rsE =/= "b00000".U & writeregW === rsE,"b01".U,"b00".U))
  forwardBE := Mux(regwriteM & rtE =/= "b00000".U & writeregM === rtE,"b10".U,Mux(regwriteW & rtE =/= "b00000".U & writeregW === rtE,"b01".U,"b00".U))

}
