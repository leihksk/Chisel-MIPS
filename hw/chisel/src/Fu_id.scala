package MIPS_chisel

import chisel3._
import chisel3.util._

class Fu_id() extends RawModule {
  val regwrite = IO(Input(Bool()))
  val writereg = IO(Input(UInt(5.W)))
  val rsD       = IO(Input(UInt(5.W)))
  val rtD       = IO(Input(UInt(5.W)))

  val forwardAD = IO(Output(Bool()))
  val forwardBD = IO(Output(Bool()))

  forwardAD := rsD =/= 0.U & rsD === writereg & regwrite
  forwardBD := rtD =/= 0.U & rtD === writereg & regwrite

}
