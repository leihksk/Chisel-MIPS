base_dir=$(abspath .)

init:
	scratchip init $(base_dir)

top=$(base_dir)/builds/Top.v

$(top): 
	make -C $(base_dir)/hw/chisel verilog

verilog: $(top)

filelist:
	scratchip filelist

sim: init verilog filelist
	iverilog -f $(base_dir)/builds/filelist/sim.f
	$(base_dir)/a.out
