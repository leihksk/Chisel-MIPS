module Dmemp(
  input         clock,
  input         we,
  input  [31:0] a,
  input  [31:0] wd,
  output [31:0] rd
);
reg [31:0] RAM[3:20];

initial
begin
	$readmemh("sw/memfile.dat",RAM);
end

assign rd = RAM[a[31:2]];
always @(posedge clock)
	if(we) RAM[a[31:2]] <= wd;
endmodule
