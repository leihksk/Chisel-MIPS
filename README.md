# Chisel实现单周期MIPS流水线处理器
---
本项目实现的MIPS指令与组件功能参看 [MIPS_test](https://github.com/leihksk/MIPS_test)


---
## 测试流程
 执行命令：   
<<<<<<< HEAD
	`$ scratchip init`   
 初始化项目文件夹   
 
=======
	`$ scratchip create (项目名)`   
 创建新的项目   
 
 将文件置于对应子目录下   
>>>>>>> cbb1d89012bee72d1cc8542a1e2f2609c93b05c0
 执行命令：   
	`$ make verilog`   
 对应的.v文件将在builds文件夹中产生   
 
 进入builds目录,执行命令：   
	`$ iverilog -f sim.f`   
	`$ ./a.out`   
<<<<<<< HEAD
 执行命令以查看波形：   
=======
 执行命令查看波形：   
>>>>>>> cbb1d89012bee72d1cc8542a1e2f2609c93b05c0
	`$ gtkwave wave.vcd`   

---
## 组件

### testbench.v
测试程序，时钟信号与重置信号输入

### dmemp.v
<<<<<<< HEAD
数据存储器，读取和更改所读取的指令

### imem.v
指令存储器，从memfile.dat文件读取16进制指令
=======
数据存储器

### imem.v
指令存储器，从memfile.dat文件读取16进制指令
>>>>>>> cbb1d89012bee72d1cc8542a1e2f2609c93b05c0
