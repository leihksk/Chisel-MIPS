package MIPS_chisel

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class Execute() extends RawModule {
  val aluoutM     = IO(Input(UInt(32.W)))//
  val resultW     = IO(Input(UInt(32.W)))//
  val rd1E        = IO(Input(UInt(32.W)))//
  val rd2E        = IO(Input(UInt(32.W)))//
  val signE       = IO(Input(UInt(32.W)))//
  val alusrcE     = IO(Input(Bool()))//
  val regdst      = IO(Input(Bool()))//
  val rdE         = IO(Input(UInt(5.W)))//
  val rtE         = IO(Input(UInt(5.W)))//
  val forwardaE   = IO(Input(UInt(2.W)))//
  val forwardbE   = IO(Input(UInt(2.W)))//
  val alucontrolE = IO(Input(UInt(3.W)))//

  val writeregE   = IO(Output(UInt(5.W)))//
  val writedataE  = IO(Output(UInt(32.W)))//
  val aluoutE     = IO(Output(UInt(32.W)))//


  val mux3A = Module (new Mux3)
  mux3A.rd := rd1E
  mux3A.resultW := resultW
  mux3A.alusrcM := aluoutM
  mux3A.forward := forwardaE
  val srcaE = mux3A.muxresult
  
  val mux3B = Module (new Mux3)
  mux3B.rd := rd2E
  mux3B.resultW := resultW
  mux3B.alusrcM := aluoutM
  mux3B.forward := forwardbE
  val mux3Bresult = mux3B.muxresult

  val mux2A = Module (new Mux2(5))
  mux2A.s := regdst
  mux2A.d0 := rtE
  mux2A.d1 := rdE
  writeregE := mux2A.y
  
  val mux2B = Module (new Mux2(32))
  mux2B.s := alusrcE
  mux2B.d0 := mux3Bresult
  mux2B.d1 := signE
  val srcbE = mux2B.y

  val alu = Module (new Alu_p)
  alu.a := srcaE
  alu.b := srcbE
  alu.alucontrol := alucontrolE
  aluoutE := alu.aluout

  writedataE := mux3Bresult
}
