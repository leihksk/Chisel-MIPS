module Imem(
  input  [5:0]  a,
  output [31:0] rd
);
reg [31:0] RAM[0:17];

initial
begin
	$readmemh("sw/memfile.dat",RAM);
end
assign rd = RAM[a];
endmodule
