package MIPS_chisel

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class Ctrl() extends Module {
  val op         = IO(Input(UInt(6.W)))
  val funct      = IO(Input(UInt(6.W)))
  val flushE     = IO(Input(Bool()))
  val zero       = IO(Input(Bool()))

  val memtoregE  = IO(Output(Bool()))
  val memtoregM  = IO(Output(Bool()))
  val memtoregW  = IO(Output(Bool()))
  val memwriteM  = IO(Output(Bool()))
  val branch     = IO(Output(Bool()))
  val pcsrc      = IO(Output(Bool()))
  val alusrc     = IO(Output(Bool()))
  val regdstE    = IO(Output(Bool()))
  val regwriteE  = IO(Output(Bool()))
  val regwriteM  = IO(Output(Bool()))
  val regwriteW  = IO(Output(Bool()))
  val jump       = IO(Output(Bool()))
  val alucontrol = IO(Output(UInt(3.W)))

  pcsrc := branch & zero
  val md = Module (new Maindec)
  md.op := op
  val aluop = md.aluop
  val memtoregD = md.memtoreg
  val memwriteD = md.memwrite
  branch := md.branch
  val alusrcD = md.alusrc
  val regdstD = md.regdst
  val regwriteD = md.regwrite
  jump := md.jump
  val ad = Module (new Aludec)
  ad.funct := funct
  ad.aluop := aluop
  val alucontrolD = ad.alucontrol
  val dereg = Module (new Floprc)
  dereg.flush := flushE
  dereg.d := Cat(memtoregD,memwriteD,regwriteD,alusrcD,regdstD,alucontrolD)
  val f8out = dereg.q
  memtoregE := f8out(7)
  val memwriteE = f8out(6)
  regwriteE := f8out(5)
  alusrc := f8out(4)
  regdstE := f8out(3)
  alucontrol := f8out(2,0)
  val emreg = Module (new Flopr(3))
  emreg.d := Cat(memtoregE,memwriteE,regwriteE)
  val f3out = emreg.q
  memtoregM := f3out(2)
  memwriteM := f3out(1)
  regwriteM := f3out(0)
  val mwreg = Module (new Flopr(2))
  mwreg.d := Cat(memtoregM,regwriteM)
  val f2out = mwreg.q
  memtoregW := f2out(1)
  regwriteW := f2out(0)

 }
