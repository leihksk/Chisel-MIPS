package MIPS_chisel

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class Hazard() extends RawModule {
  val regwriteE = IO(Input(Bool()))
  val regwriteM = IO(Input(Bool()))
  val regwriteW = IO(Input(Bool()))
  val rsE       = IO(Input(UInt(5.W)))
  val rtE       = IO(Input(UInt(5.W)))
  val rsD       = IO(Input(UInt(5.W)))
  val rtD       = IO(Input(UInt(5.W)))
  val writeregE = IO(Input(UInt(5.W)))
  val writeregM = IO(Input(UInt(5.W)))
  val writeregW = IO(Input(UInt(5.W)))
  val memtoregE = IO(Input(Bool()))
  val memtoregM = IO(Input(Bool()))
  val branchD   = IO(Input(Bool()))
  
  val forwardAD = IO(Output(Bool()))
  val forwardBD = IO(Output(Bool()))
  val forwardADW = IO(Output(Bool()))
  val forwardBDW = IO(Output(Bool()))

  val forwardAE = IO(Output(UInt(2.W)))
  val forwardBE = IO(Output(UInt(2.W)))
  val flushE    = IO(Output(Bool()))
  val stallD    = IO(Output(Bool()))
  val stallF    = IO(Output(Bool()))

  val fud = Module (new Fu_id)
  fud.regwrite := regwriteM
  fud.rsD := rsD
  fud.rtD := rtD
  fud.writereg := writeregM
  forwardAD := fud.forwardAD
  forwardBD := fud.forwardBD
  val fudW = Module (new Fu_id)
  fudW.regwrite := regwriteW
  fudW.rsD := rsD
  fudW.rtD := rtD
  fudW.writereg := writeregW
  forwardADW := fudW.forwardAD
  forwardBDW := fudW.forwardBD
  val ex = Module (new Fu_ex)
  ex.regwriteW := regwriteW
  ex.regwriteM := regwriteM
  ex.writeregW := writeregW
  ex.writeregM := writeregM
  ex.rsE := rsE
  ex.rtE := rtE
  forwardAE := ex.forwardAE
  forwardBE := ex.forwardBE

  val lwstallD = memtoregE & (rsD === rtE | rtD === rtE)
  val branchstallD = branchD & ((regwriteE & (writeregE === rsD | writeregE === rtD))|(memtoregM & (writeregM === rsD | writeregM === rtD)))
  stallD := lwstallD | branchstallD
  stallF := stallD
  flushE := stallF
}
