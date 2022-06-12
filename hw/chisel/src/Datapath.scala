package MIPS_chisel

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class Datapath() extends Module {
  val memtoregE  = IO(Input(Bool()))//
  val memtoregM  = IO(Input(Bool()))//
  val memtoregW  = IO(Input(Bool()))//
  val branch     = IO(Input(Bool()))//
  val pcsrc      = IO(Input(Bool()))//
  val alusrc     = IO(Input(Bool()))//
  val regdstE    = IO(Input(Bool()))//
  val regwriteE  = IO(Input(Bool()))//
  val regwriteM  = IO(Input(Bool()))//
  val regwriteW  = IO(Input(Bool()))//
  val jump       = IO(Input(Bool()))//
  val alucontrol = IO(Input(UInt(3.W)))//
  val instr      = IO(Input(UInt(32.W)))//
  val readdataM  = IO(Input(UInt(32.W)))//

  val zero       = IO(Output(Bool()))//
  val pc         = IO(Output(UInt(32.W)))//
  val aluout     = IO(Output(UInt(32.W)))//
  val writedataM = IO(Output(UInt(32.W)))//
  val op         = IO(Output(UInt(6.W)))//
  val funct      = IO(Output(UInt(6.W)))//
  val flushE     = IO(Output(Bool()))//

    val flush = pcsrc | jump


  val ha = Module (new Hazard)
  val forwardAD = ha.forwardAD
  val forwardBD = ha.forwardBD
  val forwardADW = ha.forwardADW
  val forwardBDW = ha.forwardBDW
  val forwardAE = ha.forwardAE
  val forwardBE = ha.forwardBE
  val stallF = ha.stallF
  val stallD = ha.stallD
  flushE := ha.flushE

  val pcbrmux = Module (new Mux2(32))
  val pcnextbr = pcbrmux.y

  val rf = Module(new Regfile)
  val rd1 = rf.rd1
  val rd2 = rf.rd2

  val pcreg = Module(new Flopenr(32))
  pc := pcreg.q

  val pcadd1 = Module(new Adder)
  val pcplus4F = pcadd1.y

  val fdp = Module (new Flopenr(32))
  val pcplus4D = fdp.q
  
  val fdi = Module (new Flopenrc(32))
  val instrD = fdi.q

  op := instrD(31,26)
  funct := instrD(5,0)
  val rsD = instrD(25,21)
  val rtD = instrD(20,16)
  val rdD = instrD(15,11)

  val se = Module(new Signnext)
  val signimmD = se.y

  val immsh = Module(new Sl2)
  val signimmsh = immsh.y

  val pcadd2 = Module(new Adder)
  val pcbranch = pcadd2.y

  val muxADW = Module(new Mux2(32))
  val srcADW = muxADW.y

  val muxBDW = Module(new Mux2(32))
  val srcBDW = muxBDW.y

  val muxAD = Module(new Mux2(32))
  val srcAD = muxAD.y

  val muxBD = Module(new Mux2(32))
  val srcBD = muxBD.y


  val comp = Module(new Comparator(32))
  zero := comp.e

  val de = Module(new Id_e)
  val srcaE = de.readdata1E
  val srcbE = de.readdata2E
  val signimmE = de.signE
  val rsE = de.rsE
  val rtE = de.rtE
  val rdE = de.rdE

  val ex = Module(new Execute)
  val writeregE = ex.writeregE
  val writedataE = ex.writedataE
  val aluoutE = ex.aluoutE

  val em = Module(new E_m)
  val writeregM = em.writeregM
  aluout := em.aluoutM
  writedataM := em.writedataM

  val mw = Module(new M_w)
  val aluoutW = mw.aluoutW
  val readdataW = mw.readdataW
  val writeregW = mw.writeregW

  val wbmux = Module(new Mux2(32))
  val result = wbmux.y

  val pcmux = Module (new Mux2(32))
  val pcnext = pcmux.y
  
  fdi.en := ~stallD
  fdi.flush := flush
  fdi.d := instr
  fdi.old_q := instrD

  se.a := instrD(15,0)

  immsh.a := signimmD

  pcadd2.a := pcplus4D
  pcadd2.b := signimmsh

  muxADW.d0 := rd1
  muxADW.d1 := result
  muxADW.s := forwardADW

  muxBDW.d0 := rd2
  muxBDW.d1 := result
  muxBDW.s := forwardBDW

  muxAD.d0 := srcADW
  muxAD.d1 := aluout
  muxAD.s := forwardAD

  muxBD.d0 := srcBDW
  muxBD.d1 := aluout
  muxBD.s := forwardBD

  comp.a := srcAD
  comp.b := srcBD

  de.flush := flushE
  de.readdata1D := srcAD
  de.readdata2D := srcBD
  de.signD := signimmD
  de.rsD  := rsD
  de.rtD := rtD
  de.rdD := rdD

  ex.alusrcE := alusrc
  ex.regdst := regdstE
  ex.resultW := result
  ex.rd1E := srcaE
  ex.alucontrolE := alucontrol
  ex.signE := signimmE
  ex.rd2E := srcbE
  ex.rdE := rdE
  ex.rtE := rtE
  ex.aluoutM := aluout
  ex.forwardaE := forwardAE
  ex.forwardbE := forwardBE

  em.writeregE := writeregE
  em.aluoutE := aluoutE
  em.writedataE := writedataE

  mw.writeregM := writeregM
  mw.aluoutM := aluout
  mw.readdataM := readdataM


  wbmux.s :=  memtoregW
  wbmux.d0 := aluoutW
  wbmux.d1 := readdataW

  fdp.en := ~stallD
  fdp.d := pcplus4F

  pcmux.d0 := pcnextbr
  pcmux.d1 := Cat(pcplus4D(31,28),instrD(25,0),"b0".U,"b0".U)
  pcmux.s := jump

  rf.we3 := regwriteW
  rf.ra1 := rsD
  rf.ra2 := rtD
  rf.wa3 := writeregW
  rf.wd3 := result

  pcreg.en := ~stallF
  pcreg.d := pcnext

  pcadd1.a := pc
  pcadd1.b := "b100".U(32.W)

  ha.regwriteM := regwriteM
  ha.regwriteE := regwriteE
  ha.regwriteW := regwriteW
  ha.rsE := rsE
  ha.rtE := rtE
  ha.rsD := rsD
  ha.rtD := rtD
  ha.writeregE := writeregE
  ha.writeregM := writeregM
  ha.writeregW := writeregW
  ha.memtoregM := memtoregM
  ha.memtoregE := memtoregE
  ha.branchD := branch

  pcbrmux.d0 := pcplus4F
  pcbrmux.d1 := pcbranch
  pcbrmux.s := pcsrc

}
