package MIPS_chisel

import chisel3._
import chisel3.util._

class Id_e() extends Module {
  val flush      = IO(Input(Bool()))
  val readdata1D = IO(Input(UInt(32.W)))
  val readdata2D = IO(Input(UInt(32.W)))
  val signD      = IO(Input(UInt(32.W)))
  val rsD        = IO(Input(UInt(5.W)))
  val rtD        = IO(Input(UInt(5.W)))
  val rdD        = IO(Input(UInt(5.W)))

  val readdata1E = IO(Output(UInt(32.W)))
  val readdata2E = IO(Output(UInt(32.W)))
  val signE      = IO(Output(UInt(32.W)))
  val rsE        = IO(Output(UInt(5.W)))
  val rtE        = IO(Output(UInt(5.W)))
  val rdE        = IO(Output(UInt(5.W)))

  //val f1 = Floprc(flush,readdata1D,32)
  //readdata1E := f1.retq
  //readdata1E := f1
  val f1 = Module(new Floprc(32))
  f1.flush := flush
  f1.d := readdata1D
  readdata1E := f1.q

  //val f2= Floprc(flush,readdata2D,32)
  //readdata2E := f2.retq
  //readdata2E := f2
  val f2 = Module(new Floprc(32))
  f2.flush := flush
  f2.d := readdata2D
  readdata2E := f2.q

  //val fsign = Floprc(flush,signD,32)
  //signE := fsign.retq
  //signE := fsign
  val fsign = Module(new Floprc(32))
  fsign.flush := flush
  fsign.d := signD
  signE := fsign.q

  //val frs = Floprc(flush,rsD,5)
  //rsE := frs.retq
  //rsE := frs
  val frs = Module(new Floprc(5))
  frs.flush := flush
  frs.d := rsD
  rsE := frs.q

  //val frt= Floprc(flush,rtD,5)
  //rtE := frt.retq
  //rtE := frt
  val frt = Module(new Floprc(5))
  frt.flush := flush
  frt.d := rtD
  rtE := frt.q

  //val frd = Floprc(flush,rdD,5)
  //rdE := frd.retq
  //rdE := frd
  val frd = Module(new Floprc(5))
  frd.flush := flush
  frd.d := rdD
  rdE := frd.q
}
