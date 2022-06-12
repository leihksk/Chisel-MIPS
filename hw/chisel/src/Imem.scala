package MIPS_chisel

import chisel3._
import chisel3.experimental._
import chisel3.util._
import chisel3.util.experimental._

class Imem() extends ExtModule {
  val a  = IO(Input(UInt(6.W)))
  val rd = IO(Output(UInt(32.W)))
}

//class Imem() extends Module {
//  val a  = IO(Input(UInt(6.W)))
//  val rd = IO(Output(UInt(32.W)))
//
//  val RAM = Mem(32,UInt(32.W))
//
//  rd := RAM.read(a)
//  loadMemoryFromFile(RAM,"memfile.dat")
//}
