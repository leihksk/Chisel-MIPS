package MIPS_chisel

import chisel3._
import chisel3.util._

class Aludec() extends RawModule {
  val funct      = IO(Input(UInt(6.W)))
  val aluop      = IO(Input(UInt(2.W)))
  val alucontrol = IO(Output(UInt(3.W)))
/*
  alucontrol := aluop match{
    case "b00" => "b010"
    case "b01" => "b110"
  }*/
 /*
  alucontrol := DontCare
  switch (aluop){
    is("b00".U){alucontrol := "b010".U(3.W)}
    is("b01".U){alucontrol := "b110".U(3.W)}
    is( _ ){
      switch(funct)
      is("b100000".U){alucontrol := "b010".U(3.W)}
    }
  }*/
       when(aluop === "b00".U){alucontrol := "b010".U(3.W)
 }.elsewhen(aluop === "b01".U){alucontrol := "b110".U(3.W)
 }.otherwise{
         when(funct === "b100000".U){alucontrol := "b010".U(3.W)
   }.elsewhen(funct === "b100010".U){alucontrol := "b110".U(3.W)
   }.elsewhen(funct === "b100100".U){alucontrol := "b000".U(3.W)
   }.elsewhen(funct === "b100101".U){alucontrol := "b001".U(3.W)
   }.elsewhen(funct === "b101010".U){alucontrol := "b111".U(3.W)
   }.otherwise{alucontrol := DontCare}
 }
}
