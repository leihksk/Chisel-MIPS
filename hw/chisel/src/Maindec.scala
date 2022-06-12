package MIPS_chisel

import chisel3._
import chisel3.util._

class Maindec() extends RawModule {
  val op       = IO(Input(UInt(6.W)))
  val memtoreg = IO(Output(Bool()))
  val memwrite = IO(Output(Bool()))
  val branch   = IO(Output(Bool()))
  val alusrc   = IO(Output(Bool()))
  val regdst   = IO(Output(Bool()))
  val regwrite = IO(Output(Bool()))
  val jump     = IO(Output(Bool()))
  val aluop    = IO(Output(UInt(2.W)))

  val op1 = op === "b000000".U
  val op2 = op === "b100011".U
  val op3 = op === "b101011".U
  val op4 = op === "b000100".U
  val op5 = op === "b001000".U
  val op6 = op === "b000010".U
  val controls = Mux1H(Array(op1 -> "b110000010".U,
    (op2 -> "b101001000".U),
    (op3 -> "b001010000".U),
    (op4 -> "b000100001".U),
    (op5 -> "b101000000".U),
    (op6 -> "b000000100".U))
    )
 /*
 val controls = Mux(op === "b000000".U,"b110000010".U,
   Mux(op === "b100011".U,"b101001000".U,
   Mux(op === "b101011".U,"b001010000".U,
   Mux(op === "b000100".U,"b000100001".U,
   Mux(op === "b001000".U,"b101000000".U,
   Mux(op === "b000010".U,"b000000100".U,DontCare))))))
 */
    /*
  when(op === "b000000".U){controls := "b110000010".U}
  .elsewhen(op === "b100011".U){controls := "b101001000".U}
  .elsewhen(op === "b101011".U){controls := "b001010000".U}
  .elsewhen(op === "b000100".U){controls := "b000100001".U}
  .elsewhen(op === "b001000".U){controls := "b101000000".U}
  .elsewhen(op === "b000010".U){controls := "b000000100".U}
  .otherwise{op := DontCare}
  */
 /*
  switch(op){
    is("b000000".U){controls := "b110000010".U}
    is("b100011".U){controls := "b101001000".U}
    is("b101011".U){controls := "b001010000".U}
    is("b000100".U){controls := "b000100001".U}
    is("b001000".U){controls := "b101000000".U}
    is("b000010".U){controls := "b000000100".U}
  }
 */ 
  regwrite := controls(8).asBool
  regdst := controls(7).asBool
  alusrc := controls(6).asBool
  branch := controls(5).asBool
  memwrite := controls(4).asBool
  memtoreg := controls(3).asBool
  jump := controls(2).asBool
  aluop := controls(1,0)
}
