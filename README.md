# Chisel实现单周期MIPS流水线处理器
---
本项目实现的MIPS指令与组件功能参看 [MIPS_test](https://github.com/leihksk/MIPS_test)


---
## 测试流程
 执行命令：   
	`$ scratchip create (项目名)   
 创建新的项目   
 
 将文件置于对应子目录下   
 执行命令：   
	`$ make verilog   
 对应的.v文件将在builds文件夹中产生   
 
 进入builds目录,执行命令：   
	`$ iverilog -f sim.f   
	`./a.out   
 执行命令查看波形：   
	$ gtkwave wave.vd

---
## 组件

### testbench.v
测试程序，时钟信号与重置信号输入

### dmemp.v
数据存储器

### imem.v
指令存储器，从memfile.dat文件读取16进制指令