package MIPS_chisel

import chisel3._
import chisel3.util._

class Top() extends Module {

  val dataaddr  = IO(Output(UInt(32.W)))
  val writedata = IO(Output(UInt(32.W)))
  val memwrite  = IO(Output(Bool()))

  val mips = Module(new Mips)
  val pc = mips.pc
  memwrite := mips.memwriteM
  dataaddr := mips.aluout
  writedata := mips.writedataM
  
  val imem = Module(new Imem)
  val instr = imem.rd

  val dmem = Module(new Dmemp)
  val readdata = dmem.rd

  mips.readdata := readdata
  mips.instr := instr

  imem.a := pc(7,2)
  
  dmem.clock := clock
  dmem.we := memwrite
  dmem.a := dataaddr
  dmem.wd := writedata

}
  
