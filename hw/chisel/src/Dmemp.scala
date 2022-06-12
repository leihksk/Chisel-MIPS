package MIPS_chisel

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.util.experimental._

class Dmemp() extends ExtModule {
  val clock = IO(Input(Clock()))
  val we = IO(Input(Bool()))
  val a  = IO(Input(UInt(32.W)))
  val wd = IO(Input(UInt(32.W)))
  val rd = IO(Output(UInt(32.W)))
/*
  val RAM = Mem(32,UInt(32.W))
  rd := RAM.read(a(31, 2))
  loadMemoryFromFile(RAM,"memfile.dat")
  when(we){
    RAM(a(31,2)) := wd
  }
  */
}
