package MIPS_chisel

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class Mips() extends Module {
  val instr      = IO(Input(UInt(32.W)))
  val readdata   = IO(Input(UInt(32.W)))
  val pc         = IO(Output(UInt(32.W)))
  val memwriteM  = IO(Output(Bool()))
  val aluout     = IO(Output(UInt(32.W)))
  val writedataM = IO(Output(UInt(32.W)))

  val c = Module(new Ctrl)
  val memtoregE = c.memtoregE
  val memtoregM = c.memtoregM
  val memtoregW = c.memtoregW
  memwriteM := c.memwriteM
  val branch = c.branch
  val pcsrc = c.pcsrc
  val alusrc = c.alusrc
  val regdstE = c.regdstE
  val regwriteE = c.regwriteE
  val regwriteM = c.regwriteM
  val regwriteW = c.regwriteW
  val jump = c.jump
  val alucontrol = c.alucontrol

  val dp = Module(new Datapath)
  val zero = dp.zero
  pc := dp.pc
  aluout := dp.aluout
  writedataM := dp.writedataM
  val op = dp.op
  val funct = dp.funct
  val flushE = dp.flushE

  c.op := op
  c.funct := funct
  c.flushE := flushE
  c.zero := zero

  dp.memtoregE := memtoregE
  dp.memtoregM := memtoregM
  dp.memtoregW := memtoregW
  dp.pcsrc := pcsrc
  dp.branch := branch
  dp.alusrc := alusrc
  dp.regdstE := regdstE
  dp.regwriteE := regwriteE
  dp.regwriteM := regwriteM
  dp.regwriteW := regwriteW
  dp.jump := jump
  dp.alucontrol := alucontrol
  dp.instr := instr
  dp.readdataM := readdata

}
