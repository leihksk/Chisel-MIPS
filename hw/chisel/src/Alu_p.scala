package MIPS_chisel

import chisel3._
import chisel3.util._

class Alu_p() extends RawModule {
  val a          = IO(Input(UInt(32.W)))
  val b          = IO(Input(UInt(32.W)))
  val alucontrol = IO(Input(UInt(3.W)))
  val aluout     = IO(Output(UInt(32.W)))

  val bb = Mux(alucontrol(2),~b,b)
  val s = a + bb + alucontrol(2)

  when(alucontrol(1) === 0.U & alucontrol(0) === 0.U){aluout := a & bb}
  .elsewhen(alucontrol(1) === 0.U & alucontrol(0) === 1.U){aluout := a | bb}
  .elsewhen(alucontrol(1) === 1.U & alucontrol(0) === 0.U){aluout := s}
  .elsewhen(alucontrol(1) === 1.U & alucontrol(0) === 1.U){aluout := s(31)}
  .otherwise{aluout := 0.U}
}
